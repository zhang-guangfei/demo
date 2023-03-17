package com.neo.demo.permission.domain.command;

import com.neo.health.common.domain.model.value.Status;
import com.neo.health.common.type.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePermissionStatusCommand implements Command {
    private String permissionId;
    private Status status;
}
