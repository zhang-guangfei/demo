package com.neo.demo.role.infrastructure.repository.mapper;

import com.neo.demo.role.infrastructure.repository.po.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends JpaRepository<RolePO, String> {
    boolean existsByRoleCode(String code);

    RolePO getByRoleCode(String roleCode);

    @Query("select roleId from RolePO order by roleCode")
    List<String> findAllRoleIds();

    @Query("select roleCode from RolePO order by roleCode")
    List<String> findAllRoleCodes();
}
