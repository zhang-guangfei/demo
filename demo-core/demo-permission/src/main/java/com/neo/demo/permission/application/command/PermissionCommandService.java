package com.neo.demo.permission.application.command;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.neo.demo.permission.domain.command.ChangePermissionStatusCommand;
import com.neo.demo.permission.domain.command.CreatePermissionCommand;
import com.neo.demo.permission.domain.command.DeletePermissionCommand;
import com.neo.demo.permission.domain.model.aggregate.Permission;
import com.neo.demo.permission.domain.model.aggregate.PermissionId;
import com.neo.demo.permission.domain.repository.PermissionRepository;
import com.neo.health.common.exception.EntityAlreadyExistException;
import com.neo.health.common.exception.EntityNotExistException;
import com.neo.health.common.exception.EntityVaildateException;
import com.neo.health.common.exception.PermissionFailureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PermissionCommandService {

    @Autowired
    private PermissionRepository permissionRepository;


    public PermissionId handlePermissionCreation(CreatePermissionCommand command) throws EntityVaildateException, PermissionFailureException {
        boolean exists = permissionRepository.existsByPermissionCode(command.getPermissionCode());
        Assert.isFalse(exists, () -> new EntityAlreadyExistException(StrUtil.format("权限创建失败，权限代码已存在：{}", command.getPermissionCode())));
        Permission permission = Permission.gen(command);
        permissionRepository.save(permission);
        // TODO 领域事件
        log.info("《领域事件》【权限模块】 权限创建成功：\n{}", JSONUtil.toJsonPrettyStr(permission));
        return permission.getId();
    }

    @Transactional
    public void handleRoleStatusChange(ChangePermissionStatusCommand command) throws EntityVaildateException, PermissionFailureException {
        Permission permission = permissionRepository.findByPermissionId(command.getPermissionId());
        Assert.notNull(permission, () -> new EntityNotExistException("权限状态修改失败，权限不存在"));
        permission.changeStatus(command);
        permissionRepository.save(permission);
        // TODO 领域事件
        log.info("《领域事件》【权限模块】 权限状态修改成功：{}", permission.getStatus().getName());
    }

    @Transactional
    public void handPermissionDelete(DeletePermissionCommand command) throws EntityVaildateException, PermissionFailureException {
        Permission permission = permissionRepository.findByPermissionId(command.getPermissionId());
        Assert.notNull(permission, () -> new EntityNotExistException("权限删除失败，权限不存在"));
        permissionRepository.deleteByPermissionId(permission.getId());
        // TODO 领域事件
        log.info("《领域事件》【权限模块】 权限删除成功：{}", permission.getId());
    }


}
