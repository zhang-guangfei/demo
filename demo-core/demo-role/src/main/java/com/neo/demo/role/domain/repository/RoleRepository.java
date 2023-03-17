package com.neo.demo.role.domain.repository;

import com.neo.demo.role.domain.model.aggregate.Role;
import com.neo.demo.role.domain.model.aggregate.RoleId;
import com.neo.health.common.exception.RoleFailureException;

import java.util.List;

public interface RoleRepository {
    void save(Role role) throws RoleFailureException;

    void deleteByRoleId(RoleId roleId) throws RoleFailureException;

    boolean existsByRoleCode(String code);

    boolean existsByRoleId(RoleId id);

    Role findByRoleId(String roleId);

    Role findByRoleCode(String code);

    List<RoleId> findAllRoleIds();

    List<String> findAllRoleCodes();

    List<Role> findAll();
}
