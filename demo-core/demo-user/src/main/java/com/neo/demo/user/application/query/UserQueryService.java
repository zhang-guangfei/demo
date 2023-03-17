package com.neo.demo.user.application.query;

import com.neo.demo.user.domain.model.aggregate.User;
import com.neo.demo.user.domain.model.aggregate.UserId;
import com.neo.demo.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryService {

    @Autowired
    private UserRepository userRepository;

    public User findByUserId(String roleId) {
        return userRepository.findByUserId(roleId);
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<UserId> findAllUserIds() {
        return userRepository.findAllUserIds();
    }

    public List<String> findAllUsernames() {
        return userRepository.findAllUsernames();
    }


}
