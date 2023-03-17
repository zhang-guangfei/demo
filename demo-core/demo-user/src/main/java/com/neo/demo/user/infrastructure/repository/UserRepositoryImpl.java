package com.neo.demo.user.infrastructure.repository;

import cn.hutool.core.lang.Assert;
import com.neo.demo.user.domain.model.aggregate.User;
import com.neo.demo.user.domain.model.aggregate.UserId;
import com.neo.demo.user.domain.repository.UserRepository;
import com.neo.demo.user.infrastructure.repository.factory.UserFactory;
import com.neo.demo.user.infrastructure.repository.mapper.UserMapper;
import com.neo.demo.user.infrastructure.repository.po.UserPO;
import com.neo.health.common.exception.UserFailureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void save(User user) throws UserFailureException {
        UserPO userPO = UserFactory.toPO(user);
        Assert.notNull(userPO, () -> new UserFailureException("参数不允许为null"));
        userMapper.saveAndFlush(userPO);
    }

    @Override
    @Transactional
    public void deleteByUserId(UserId userId) throws UserFailureException {
        Assert.notNull(userId, () -> new UserFailureException("参数不允许为null"));
        userMapper.deleteById(userId.getUserId());
    }

    @Override
    public User findByUserId(String userId) {
        UserPO userPO = userMapper.findById(userId).orElse(null);
        User user = UserFactory.toDO(userPO);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        UserPO userPO = userMapper.getByUsername(username);
        User user = UserFactory.toDO(userPO);
        return user;
    }

    @Override
    public boolean existsByUserId(UserId id) {
        return userMapper.existsByUsername(id.getUserId());
    }

    @Override
    public boolean existsByUsername(String username) {
        return userMapper.existsByUsername(username);
    }

    @Override
    public List<UserId> findAllUserIds() {
        List<String> userIds = userMapper.findAllUserIds();
        List<UserId> userIdList = userIds.stream().map(id -> new UserId(id)).collect(Collectors.toList());
        return userIdList;
    }

    @Override
    public List<String> findAllUsernames() {
        return userMapper.findAllUsernames();
    }

    @Override
    public List<User> findAll() {
        List<String> userIds = userMapper.findAllUserIds();
        List<User> users = userIds.stream().map(id -> findByUserId(id)).collect(Collectors.toList());
        return users;
    }

}
