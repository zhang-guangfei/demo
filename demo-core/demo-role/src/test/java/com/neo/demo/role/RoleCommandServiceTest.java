package com.neo.demo.role;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.neo.demo.role.application.command.RoleCommandService;
import com.neo.demo.role.application.query.RoleQueryService;
import com.neo.demo.role.domain.command.ChangeRoleStatusCommand;
import com.neo.demo.role.domain.command.CreateRoleCommand;
import com.neo.demo.role.domain.command.DeleteRoleCommand;
import com.neo.demo.role.domain.model.aggregate.Role;
import com.neo.health.common.domain.model.value.Status;
import com.neo.health.common.exception.EntityAlreadyExistException;
import com.neo.health.common.exception.EntityVaildateException;
import com.neo.health.common.exception.RoleFailureException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class RoleCommandServiceTest {

    @Autowired
    RoleCommandService roleCommandService;
    @Autowired
    RoleQueryService roleQueryService;

    @Test
    void test1_1() {
        List<Role> all = roleQueryService.findAll();
        Assert.isTrue(CollectionUtil.isNotEmpty(all));
        log.info(JSONUtil.toJsonPrettyStr(all));
    }

    @Test
    void test1_1_1() {
        List<String> all = roleQueryService.findAllRoleCodes();
        Assert.isTrue(CollectionUtil.isNotEmpty(all));
        log.info(JSONUtil.toJsonPrettyStr(all));
    }


    @Test
    void test1_2() {
        Role role = roleQueryService.findByRoleCode("public");
        Assert.notNull(role);
        log.info(JSONUtil.toJsonPrettyStr(role));
    }

    @Test
    void test1_3() {
        Role role = roleQueryService.findByRoleCode("public");
        role = roleQueryService.findByRoleId(role.getId().getRoleId());
        Assert.notNull(role);
        log.info(JSONUtil.toJsonPrettyStr(role));
    }

    @Test
    void test2_1() throws EntityAlreadyExistException, RoleFailureException {
        String code = "public";
        CreateRoleCommand command = new CreateRoleCommand(code, "公共角色", "每个用户都默认拥有的用户");
        roleCommandService.handleRoleCreation(command);
        Role role = roleQueryService.findByRoleCode(code);
        Assert.notNull(role);
        log.info("创建成功：\n{}", JSONUtil.toJsonPrettyStr(role));
    }


    @Test
    void test2_2() throws EntityAlreadyExistException, RoleFailureException {
        String code = "reader";
        CreateRoleCommand command = new CreateRoleCommand(code, "只读角色", "拥有读数据的权限");
        roleCommandService.handleRoleCreation(command);
        Role role = roleQueryService.findByRoleCode(code);
        Assert.notNull(role);
        log.info("创建成功：\n{}", JSONUtil.toJsonPrettyStr(role));
    }

    @Test
    void test2_3() throws EntityAlreadyExistException, RoleFailureException {
        String code = "writer";
        CreateRoleCommand command = new CreateRoleCommand(code, "只写角色", "拥有修改数据的权限");
        roleCommandService.handleRoleCreation(command);
        Role role = roleQueryService.findByRoleCode(code);
        Assert.notNull(role);
        log.info("创建成功：\n{}", JSONUtil.toJsonPrettyStr(role));
    }
    @Test
    void test2_4() throws EntityAlreadyExistException, RoleFailureException {
        String code = "sa";
        CreateRoleCommand command = new CreateRoleCommand(code, "超级管理员", "拥有系统的所有权限");
        roleCommandService.handleRoleCreation(command);
        Role role = roleQueryService.findByRoleCode(code);
        Assert.notNull(role);
        log.info("创建成功：\n{}", JSONUtil.toJsonPrettyStr(role));
    }

    @Test
    void test3_1() throws EntityVaildateException, RoleFailureException {
        String code = "updater";
        Role role = roleQueryService.findByRoleCode(code);
        ChangeRoleStatusCommand command = new ChangeRoleStatusCommand(role.getId().getRoleId(), Status.DISABLE);
        roleCommandService.handleRoleStatusChange(command);
        role = roleQueryService.findByRoleCode(code);
        Assert.notNull(role);
        log.info("修改成功：\n{}", JSONUtil.toJsonPrettyStr(role));
    }

    @Test
    void test3_2() throws EntityVaildateException, RoleFailureException {
        String code = "updater";
        Role role = roleQueryService.findByRoleCode(code);
        DeleteRoleCommand command = new DeleteRoleCommand(role.getId().getRoleId());
        roleCommandService.handRoleDelete(command);
        boolean exists = roleQueryService.existsByRoleCode(code);
        Assert.isFalse(exists);
        log.info("删除成功");
    }


}