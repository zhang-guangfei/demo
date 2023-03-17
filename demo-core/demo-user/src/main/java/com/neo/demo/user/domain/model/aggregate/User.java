package com.neo.demo.user.domain.model.aggregate;

import com.neo.demo.user.domain.command.ChangeUserStatusCommand;
import com.neo.demo.user.domain.command.CreateUserCommand;
import com.neo.demo.user.domain.model.entity.RoleProfile;
import com.neo.health.common.domain.model.value.Status;
import com.neo.health.common.exception.IdentityUnmatchedException;
import com.neo.health.common.exception.StatusDuplicatedException;
import com.neo.health.common.type.Aggregate;
import com.neo.health.common.utils.UUIDGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * 角色聚合
 */
@Data
@Builder
@AllArgsConstructor
public class User implements Aggregate {
    private UserId id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Status status;

    private List<RoleProfile> roles;

    private User(CreateUserCommand command) {
        this.id = new UserId(UUIDGenerator.userId());
        this.username = command.getUsername();
        this.password = command.getPassword();
        this.phone = command.getPhone();
        this.email = command.getEmail();
        this.status = Status.ENABLE;
        this.roles = Collections.emptyList();
    }

    public static User gen(CreateUserCommand command) {
        return new User(command);
    }

    public void changeStatus(ChangeUserStatusCommand command) throws IdentityUnmatchedException, StatusDuplicatedException {
        checkId(command.getUserId());
        checkStatus(command.getStatus());
        this.status = command.getStatus();
    }

    private void checkStatus(Status status) throws StatusDuplicatedException {
        if (this.status.equals(status)) {
            throw new StatusDuplicatedException("用户状态本身就是" + status.getName());
        }
    }

    private void checkId(String roleId) throws IdentityUnmatchedException {
        if (!StringUtils.equals(this.id.getUserId(), roleId)) {
            throw new IdentityUnmatchedException("用户ID不正确");
        }
    }

}
