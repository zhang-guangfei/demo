package com.neo.demo.permission.domain.command;

import com.neo.health.common.type.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeletePermissionCommand implements Command {
    private String permissionId;
}
