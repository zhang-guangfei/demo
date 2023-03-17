package com.neo.demo.role.infrastructure.repository.factory;

import com.neo.demo.role.domain.model.aggregate.Role;
import com.neo.demo.role.domain.model.aggregate.RoleId;
import com.neo.demo.role.infrastructure.repository.po.RolePO;

public class RoleFactory {
    public static RolePO toPO(Role role) {
        if (role == null) return null;
        RolePO rolePO = new RolePO();
        rolePO.setRoleId(role.getId().getRoleId());
        rolePO.setRoleCode(role.getCode());
        rolePO.setRoleName(role.getName());
        rolePO.setRoleDesc(role.getDescription());
        rolePO.setStatus(role.getStatus());
        return rolePO;
    }

    public static Role toDo(RolePO rolePO) {
        if (rolePO == null) return null;
        Role role = Role.builder()
                .id(new RoleId(rolePO.getRoleId()))
                .code(rolePO.getRoleCode())
                .name(rolePO.getRoleName())
                .description(rolePO.getRoleDesc())
                .status(rolePO.getStatus())
                .build();
        return role;
    }


}
