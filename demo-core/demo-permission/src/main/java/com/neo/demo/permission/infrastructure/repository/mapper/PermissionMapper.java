package com.neo.demo.permission.infrastructure.repository.mapper;

import com.neo.demo.permission.infrastructure.repository.po.PermissionPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends JpaRepository<PermissionPO, String> {
    PermissionPO getByPermissionCode(String code);

    boolean existsByPermissionCode(String code);

    @Query("select permissionId from PermissionPO ")
    List<String> findAllPermissionIds();

    @Query("select permissionCode from PermissionPO ")
    List<String> findAllPermissionCodes();


}
