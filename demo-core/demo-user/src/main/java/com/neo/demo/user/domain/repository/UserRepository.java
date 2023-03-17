package com.neo.demo.user.domain.repository;

import com.neo.demo.user.domain.model.aggregate.User;
import com.neo.demo.user.domain.model.aggregate.UserId;
import com.neo.health.common.exception.UserFailureException;

import java.util.List;

public interface UserRepository {
    void save(User user) throws UserFailureException;

    void deleteByUserId(UserId id) throws UserFailureException;

    User findByUserId(String userId);

    User findByUsername(String username);

    boolean existsByUserId(UserId id);

    boolean existsByUsername(String username);

    List<UserId> findAllUserIds();

    List<String> findAllUsernames();

    List<User> findAll();
}
