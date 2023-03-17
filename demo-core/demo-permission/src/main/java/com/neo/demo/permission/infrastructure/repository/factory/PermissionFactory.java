package com.neo.demo.permission.infrastructure.repository.factory;

import com.neo.demo.permission.domain.model.aggregate.Permission;
import com.neo.demo.permission.domain.model.aggregate.PermissionId;
import com.neo.demo.permission.infrastructure.repository.po.PermissionPO;

public class PermissionFactory {

    public static PermissionPO toPO(Permission permission) {
        if (permission == null) return null;
        PermissionPO permissionPO = new PermissionPO();
        permissionPO.setPermissionId(permission.getId().getPermissionId());
        permissionPO.setPermissionCode(permission.getCode());
        permissionPO.setPermissionName(permission.getName());
        permissionPO.setPermissionDesc(permission.getDescription());
        permissionPO.setPermissionStatus(permission.getStatus());
        return permissionPO;
    }

    public static Permission toDO(PermissionPO permissionPO) {
        if (permissionPO == null) return null;
        PermissionId permissionId = new PermissionId(permissionPO.getPermissionId());
        Permission permission = Permission.builder()
                .id(permissionId)
                .code(permissionPO.getPermissionCode())
                .name(permissionPO.getPermissionName())
                .description(permissionPO.getPermissionDesc())
                .status(permissionPO.getPermissionStatus())
                .build();
        return permission;
    }


}
