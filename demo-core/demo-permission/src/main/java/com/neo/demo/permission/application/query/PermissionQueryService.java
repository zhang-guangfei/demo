package com.neo.demo.permission.application.query;

import com.neo.demo.permission.domain.model.aggregate.Permission;
import com.neo.demo.permission.domain.model.aggregate.PermissionId;
import com.neo.demo.permission.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionQueryService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission findByPermissionId(String permissionId) {
        return permissionRepository.findByPermissionId(permissionId);
    }

    public Permission findByPermissionCode(String code) {
        return permissionRepository.findByPermissionCode(code);
    }

    public boolean existsByPermissionCode(PermissionId id) {
        return permissionRepository.existsByPermissionId(id);
    }

    public boolean existsByPermissionCode(String code) {
        return permissionRepository.existsByPermissionCode(code);
    }

    public List<PermissionId> findAllPermissionIds() {
        return permissionRepository.findAllPermissionIds();
    }

    public List<String> findAllPermissionCodes() {
        return permissionRepository.findAllPermissionCodes();
    }

    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

}
