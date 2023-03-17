package com.neo.demo.user.application.command;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.neo.demo.user.domain.command.ChangeUserStatusCommand;
import com.neo.demo.user.domain.command.CreateUserCommand;
import com.neo.demo.user.domain.command.DeleteUserCommand;
import com.neo.demo.user.domain.model.aggregate.User;
import com.neo.demo.user.domain.model.aggregate.UserId;
import com.neo.demo.user.domain.repository.UserRepository;
import com.neo.health.common.exception.EntityAlreadyExistException;
import com.neo.health.common.exception.EntityNotExistException;
import com.neo.health.common.exception.EntityVaildateException;
import com.neo.health.common.exception.UserFailureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户模块
 */
@Slf4j
@Service
public class UserCommandService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserId handleRoleCreation(CreateUserCommand command) throws EntityVaildateException, UserFailureException {
        boolean exists = userRepository.existsByUsername(command.getUsername());
        Assert.isFalse(exists, () -> new EntityAlreadyExistException(StrUtil.format("用户创建失败，用户名已存在：{}", command.getUsername())));
        User user = User.gen(command);
        userRepository.save(user);
        // TODO 领域事件
        log.info("《领域事件》【用户模块】 用户创建成功：\n{}", JSONUtil.toJsonPrettyStr(user));
        return user.getId();
    }

    @Transactional
    public void handleUserStatusChange(ChangeUserStatusCommand command) throws EntityVaildateException, UserFailureException {
        User user = userRepository.findByUserId(command.getUserId());
        Assert.notNull(user, () -> new EntityNotExistException("用户状态修改失败，用户不存在"));
        user.changeStatus(command);
        userRepository.save(user);
        // TODO 领域事件
        log.info("《领域事件》【用户模块】 用户状态修改成功：{}", user.getStatus().getName());
    }

    @Transactional
    public void handUserDelete(DeleteUserCommand command) throws EntityVaildateException, UserFailureException {
        User user = userRepository.findByUserId(command.getUserId());
        Assert.notNull(user, () -> new EntityNotExistException("用户删除失败，用户不存在"));
        userRepository.deleteByUserId(user.getId());
        // TODO 领域事件
        log.info("《领域事件》【用户模块】 用户删除成功：{}", user.getId());
    }


}
