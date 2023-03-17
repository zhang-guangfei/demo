package com.neo.demo.role.application.command;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.neo.demo.role.domain.command.ChangeRoleStatusCommand;
import com.neo.demo.role.domain.command.CreateRoleCommand;
import com.neo.demo.role.domain.command.DeleteRoleCommand;
import com.neo.demo.role.domain.model.aggregate.Role;
import com.neo.demo.role.domain.model.aggregate.RoleId;
import com.neo.demo.role.domain.repository.RoleRepository;
import com.neo.health.common.exception.EntityAlreadyExistException;
import com.neo.health.common.exception.EntityNotExistException;
import com.neo.health.common.exception.EntityVaildateException;
import com.neo.health.common.exception.RoleFailureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RoleCommandService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public RoleId handleRoleCreation(CreateRoleCommand command) throws EntityAlreadyExistException, RoleFailureException {
        boolean exists = roleRepository.existsByRoleCode(command.getRoleCode());
        Assert.isFalse(exists, () -> new EntityAlreadyExistException(StrUtil.format("角色创建失败，角色代码已存在：{}", command.getRoleCode())));
        Role role = Role.gen(command);
        roleRepository.save(role);
        // TODO 领域事件
        log.info("《领域事件》【角色模块】 角色创建成功：\n{}", JSONUtil.toJsonPrettyStr(role));
        return role.getId();
    }

    @Transactional
    public void handleRoleStatusChange(ChangeRoleStatusCommand command) throws EntityVaildateException, RoleFailureException {
        Role role = roleRepository.findByRoleId(command.getRoleId());
        Assert.notNull(role, () -> new EntityNotExistException("角色状态修改失败，角色不存在"));
        role.changeStatus(command);
        roleRepository.save(role);
        // TODO 领域事件
        log.info("《领域事件》【角色模块】 角色状态修改成功：{}", role.getStatus().getName());
    }

    @Transactional
    public void handRoleDelete(DeleteRoleCommand command) throws EntityVaildateException, RoleFailureException {
        Role role = roleRepository.findByRoleId(command.getRoleId());
        Assert.notNull(role, () -> new EntityNotExistException("角色删除失败，角色不存在"));
        roleRepository.deleteByRoleId(role.getId());
        // TODO 领域事件
        log.info("《领域事件》【角色模块】 角色删除成功：{}", role.getId());
    }


}
