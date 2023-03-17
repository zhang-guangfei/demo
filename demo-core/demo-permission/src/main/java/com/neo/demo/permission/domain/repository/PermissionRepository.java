package com.neo.demo.permission.domain.repository;

import com.neo.demo.permission.domain.model.aggregate.Permission;
import com.neo.demo.permission.domain.model.aggregate.PermissionId;
import com.neo.health.common.exception.PermissionFailureException;

import java.util.List;

public interface PermissionRepository {


    void save(Permission permission) throws PermissionFailureException;

    void deleteByPermissionId(PermissionId permissionId) throws PermissionFailureException;

    Permission findByPermissionId(String permissionId);

    Permission findByPermissionCode(String code);

    boolean existsByPermissionCode(String code);

    boolean existsByPermissionId(PermissionId id);

    List<PermissionId> findAllPermissionIds();

    List<String> findAllPermissionCodes();

    List<Permission> findAll();


}
