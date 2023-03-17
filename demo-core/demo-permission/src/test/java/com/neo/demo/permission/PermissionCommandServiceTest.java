package com.neo.demo.permission;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.neo.demo.permission.application.command.PermissionCommandService;
import com.neo.demo.permission.application.query.PermissionQueryService;
import com.neo.demo.permission.domain.command.ChangePermissionStatusCommand;
import com.neo.demo.permission.domain.command.CreatePermissionCommand;
import com.neo.demo.permission.domain.command.DeletePermissionCommand;
import com.neo.demo.permission.domain.model.aggregate.Permission;
import com.neo.health.common.domain.model.value.Status;
import com.neo.health.common.exception.EntityVaildateException;
import com.neo.health.common.exception.PermissionFailureException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class PermissionCommandServiceTest {

    @Autowired
    private PermissionCommandService permissionCommandService;
    @Autowired
    private PermissionQueryService permissionQueryService;

    @Test
    void test1_1() throws EntityVaildateException, PermissionFailureException {
        String code = "insert";
        CreatePermissionCommand command = new CreatePermissionCommand(code, "增", "增加数据");
        permissionCommandService.handlePermissionCreation(command);
        Permission permission = permissionQueryService.findByPermissionCode(code);
        Assert.notNull(permission, "查询失败");
    }

    @Test
    void test1_2() throws EntityVaildateException, PermissionFailureException {
        String code = "select123";
        CreatePermissionCommand command = new CreatePermissionCommand(code, "查", "查询数据");
        permissionCommandService.handlePermissionCreation(command);
        Permission permission = permissionQueryService.findByPermissionCode(code);
        Assert.notNull(permission, "查询失败");
    }

    @Test
    void test1_3() throws EntityVaildateException, PermissionFailureException {
        String code = "update";
        CreatePermissionCommand command = new CreatePermissionCommand(code, "改", "修改数据");
        permissionCommandService.handlePermissionCreation(command);
        Permission permission = permissionQueryService.findByPermissionCode(code);
        Assert.notNull(permission, "查询失败");
    }

    @Test
    void test1_4() throws EntityVaildateException, PermissionFailureException {
        String code = "delete";
        CreatePermissionCommand command = new CreatePermissionCommand(code, "删", "删除数据");
        permissionCommandService.handlePermissionCreation(command);
        Permission permission = permissionQueryService.findByPermissionCode(code);
        Assert.notNull(permission, "查询失败");
    }

    @Test
    void test2_1() {
        String code = "insert";
        Permission permission = permissionQueryService.findByPermissionCode(code);
        Assert.notNull(permission, "查询失败");
        log.info("查询成功：\n{}", JSONUtil.toJsonPrettyStr(permission));
    }

    @Test
    void test2_2() throws EntityVaildateException, PermissionFailureException {
        String code = "insert";
        Permission permission = permissionQueryService.findByPermissionCode(code);
        Assert.notNull(permission, "查询失败");
        log.info("查询成功：\n{}", JSONUtil.toJsonPrettyStr(permission));
        ChangePermissionStatusCommand command = new ChangePermissionStatusCommand(permission.getId().getPermissionId(), Status.ENABLE);
        permissionCommandService.handleRoleStatusChange(command);
        permission = permissionQueryService.findByPermissionCode(code);
        log.info("查询成功：\n{}", JSONUtil.toJsonPrettyStr(permission));
    }

    @Test
    void test2_3() throws EntityVaildateException, PermissionFailureException {
        String code = "select123";
        Permission permission = permissionQueryService.findByPermissionCode(code);
        Assert.notNull(permission, "查询失败");
        log.info("查询成功：\n{}", JSONUtil.toJsonPrettyStr(permission));
        DeletePermissionCommand command = new DeletePermissionCommand(permission.getId().getPermissionId());
        permissionCommandService.handPermissionDelete(command);
        boolean exists = permissionQueryService.existsByPermissionCode(code);
        Assert.isFalse(exists, () -> new RuntimeException("删除失败"));
        log.info("删除成功");
    }


}