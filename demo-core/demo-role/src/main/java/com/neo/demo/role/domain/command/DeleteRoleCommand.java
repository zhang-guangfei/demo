package com.neo.demo.role.domain.command;

import com.neo.health.common.type.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteRoleCommand implements Command {
    private String roleId;
}
