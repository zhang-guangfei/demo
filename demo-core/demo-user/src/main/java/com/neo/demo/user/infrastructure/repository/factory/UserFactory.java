package com.neo.demo.user.infrastructure.repository.factory;

import com.neo.demo.user.domain.model.aggregate.User;
import com.neo.demo.user.domain.model.aggregate.UserId;
import com.neo.demo.user.infrastructure.repository.po.UserPO;

import java.util.Collections;

public class UserFactory {

    public static UserPO toPO(User user) {
        if (user == null) return null;
        UserPO userPO = new UserPO();
        userPO.setUserId(user.getId().getUserId());
        userPO.setUsername(user.getUsername());
        userPO.setPassword(user.getPassword());
        userPO.setPhone(user.getPhone());
        userPO.setEmail(user.getEmail());
        userPO.setStatus(user.getStatus());
        return userPO;
    }

    public static User toDO(UserPO userPO) {
        if (userPO == null) return null;
        User user = User.builder()
                .id(new UserId(userPO.getUserId()))
                .username(userPO.getUsername())
                .password(userPO.getPassword())
                .email(userPO.getEmail())
                .status(userPO.getStatus())
                .roles(Collections.emptyList())
                .build();
        return user;
    }


}
