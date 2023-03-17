package com.neo.demo.user.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteUserCommand {
    private String userId;
}
