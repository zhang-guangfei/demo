package com.neo.demo.role.domain.command;

import com.neo.health.common.type.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateRoleCommand implements Command {
    private String roleCode;
    private String roleName;
    private String description;

}
