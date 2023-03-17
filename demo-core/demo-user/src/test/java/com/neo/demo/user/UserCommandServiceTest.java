package com.neo.demo.user;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.neo.demo.user.application.command.UserCommandService;
import com.neo.demo.user.application.query.UserQueryService;
import com.neo.demo.user.domain.command.CreateUserCommand;
import com.neo.demo.user.domain.model.aggregate.User;
import com.neo.health.common.exception.EntityVaildateException;
import com.neo.health.common.exception.UserFailureException;
import com.neo.health.common.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class UserCommandServiceTest {


    @Autowired
    private UserCommandService userCommandService;
    @Autowired
    private UserQueryService userQueryService;


    @Test
    void test1_1() {
        List<User> all = userQueryService.findAll();
        Assert.isTrue(CollectionUtil.isNotEmpty(all));
        log.info(JSONUtil.toJsonPrettyStr(all));
    }

    @Test
    void test1_2() {
        List<String> all = userQueryService.findAllUsernames();
        Assert.isTrue(CollectionUtil.isNotEmpty(all));
        log.info(JSONUtil.toJsonPrettyStr(all));
    }

    @Test
    void test1_3() {
        User user = userQueryService.findByUsername("tom");
        Assert.notNull(user);
        log.info(JSONUtil.toJsonPrettyStr(user));
    }

    @Test
    void test2_1() throws UserFailureException, EntityVaildateException {
        String username = "admin";
        CreateUserCommand command = new CreateUserCommand(username, DigestUtil.md5Hex(RandomUtils.str(6, 20)), RandomUtil.randomNumbers(10), RandomUtils.str(6, 10) + "@demo.com");
        userCommandService.handleRoleCreation(command);
        User user = userQueryService.findByUsername(username);
        Assert.notNull(user);
        log.info(JSONUtil.toJsonPrettyStr(user));
    }


    @Test
    void test2_2() throws UserFailureException, EntityVaildateException {
        String username = "jack";
        CreateUserCommand command = new CreateUserCommand(username, DigestUtil.md5Hex(RandomUtils.str(6, 20)), RandomUtil.randomNumbers(10), RandomUtils.str(6, 10) + "@demo.com");
        userCommandService.handleRoleCreation(command);
        User user = userQueryService.findByUsername(username);
        Assert.notNull(user);
        log.info(JSONUtil.toJsonPrettyStr(user));
    }


    @Test
    void test2_3() throws UserFailureException, EntityVaildateException {
        String username = "admin";
        CreateUserCommand command = new CreateUserCommand(username, DigestUtil.md5Hex(RandomUtils.str(6, 20)), RandomUtil.randomNumbers(10), RandomUtils.str(6, 10) + "@demo.com");
        userCommandService.handleRoleCreation(command);
        User user = userQueryService.findByUsername(username);
        Assert.notNull(user);
        log.info(JSONUtil.toJsonPrettyStr(user));
    }


}