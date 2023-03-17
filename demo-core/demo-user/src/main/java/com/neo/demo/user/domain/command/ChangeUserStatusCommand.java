package com.neo.demo.user.domain.command;

import com.neo.health.common.domain.model.value.Status;
import com.neo.health.common.type.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeUserStatusCommand implements Command {
    private String userId;
    private Status status;

}
