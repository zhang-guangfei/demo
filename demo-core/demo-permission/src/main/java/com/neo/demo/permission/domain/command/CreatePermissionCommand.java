package com.neo.demo.permission.domain.command;

import com.neo.health.common.type.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatePermissionCommand implements Command {

    private String permissionCode;
    private String permissionName;
    private String description;

}
