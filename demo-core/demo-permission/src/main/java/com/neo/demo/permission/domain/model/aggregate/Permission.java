package com.neo.demo.permission.domain.model.aggregate;

import cn.hutool.core.lang.Assert;
import com.neo.demo.permission.domain.command.ChangePermissionStatusCommand;
import com.neo.demo.permission.domain.command.CreatePermissionCommand;
import com.neo.health.common.domain.model.value.Status;
import com.neo.health.common.type.Aggregate;
import com.neo.health.common.exception.IdentityUnmatchedException;
import com.neo.health.common.exception.StatusDuplicatedException;
import com.neo.health.common.utils.UUIDGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 角色聚合
 */
@Data
@Builder
@AllArgsConstructor
public class Permission implements Aggregate {
    private PermissionId id;
    private String code;
    private String name;
    private String description;
    private Status status;

    private Permission(CreatePermissionCommand command) {
        String permissionId = UUIDGenerator.permissionId();
        this.id = new PermissionId(permissionId);
        this.code = command.getPermissionCode();
        this.name = command.getPermissionName();
        this.description = command.getDescription();
        this.status = Status.ENABLE;
    }

    public static Permission gen(CreatePermissionCommand command) {
        return new Permission(command);
    }

    public void changeStatus(ChangePermissionStatusCommand command) throws IdentityUnmatchedException, StatusDuplicatedException {
        checkId(command.getPermissionId());
        checkStatus(command.getStatus());
        this.status = command.getStatus();
    }

    private void checkStatus(Status status) throws StatusDuplicatedException {
        Assert.notEquals(this.status, status, () -> new StatusDuplicatedException("权限状态本身就是" + status.getName()));
    }

    private void checkId(String roleId) throws IdentityUnmatchedException {
        Assert.equals(this.id.getPermissionId(), roleId, () -> new IdentityUnmatchedException("权限ID不正确"));
    }


}
