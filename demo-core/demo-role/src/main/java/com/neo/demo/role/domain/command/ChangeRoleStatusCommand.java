package com.neo.demo.role.domain.command;

import com.neo.health.common.domain.model.value.Status;
import com.neo.health.common.type.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeRoleStatusCommand implements Command {
    private String roleId;
    private Status status;
}
