package com.neo.demo.user.domain.command;

import com.neo.health.common.type.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserCommand implements Command {
    private String username;
    private String password;
    private String phone;
    private String email;

}
