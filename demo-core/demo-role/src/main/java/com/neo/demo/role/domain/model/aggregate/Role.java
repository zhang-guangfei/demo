package com.neo.demo.role.domain.model.aggregate;

import com.neo.demo.role.domain.command.ChangeRoleStatusCommand;
import com.neo.demo.role.domain.command.CreateRoleCommand;
import com.neo.health.common.domain.model.value.Status;
import com.neo.health.common.exception.IdentityUnmatchedException;
import com.neo.health.common.exception.StatusDuplicatedException;
import com.neo.health.common.type.Aggregate;
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
public class Role implements Aggregate {
    private RoleId id;

    private String code;

    private String name;

    private String description;

    private Status status;


    private Role(CreateRoleCommand command) {
        this.id = new RoleId(UUIDGenerator.roleId());
        this.code = command.getRoleCode();
        this.name = command.getRoleName();
        this.description = command.getDescription();
        this.status = Status.ENABLE;
    }

    public static Role gen(CreateRoleCommand command) {
        return new Role(command);
    }

    public void changeStatus(ChangeRoleStatusCommand command) throws IdentityUnmatchedException, StatusDuplicatedException {
        checkId(command.getRoleId());
        checkStatus(command.getStatus());
        this.status = command.getStatus();
    }


    private void checkStatus(Status status) throws StatusDuplicatedException {
        if (this.status.equals(status)) {
            throw new StatusDuplicatedException("角色状态本身就是" + status.getName());
        }
    }

    private void checkId(String roleId) throws IdentityUnmatchedException {
        if (!StringUtils.equals(this.id.getRoleId(), roleId)) {
            throw new IdentityUnmatchedException("角色ID不正确");
        }
    }


}
