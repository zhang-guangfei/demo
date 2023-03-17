package com.neo.demo.permission.infrastructure.repository;

import cn.hutool.core.lang.Assert;
import com.neo.demo.permission.domain.model.aggregate.Permission;
import com.neo.demo.permission.domain.model.aggregate.PermissionId;
import com.neo.demo.permission.domain.repository.PermissionRepository;
import com.neo.demo.permission.infrastructure.repository.factory.PermissionFactory;
import com.neo.demo.permission.infrastructure.repository.mapper.PermissionMapper;
import com.neo.demo.permission.infrastructure.repository.po.PermissionPO;
import com.neo.health.common.exception.PermissionFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    @Transactional
    public void save(Permission permission) throws PermissionFailureException {
        PermissionPO permissionPO = PermissionFactory.toPO(permission);
        Assert.notNull(permissionPO, () -> new PermissionFailureException("参数不允许为null"));
        permissionMapper.save(permissionPO);
    }

    @Override
    @Transactional
    public void deleteByPermissionId(PermissionId permissionId) throws PermissionFailureException {
        Assert.notNull(permissionId, () -> new PermissionFailureException("参数不允许为null"));
        permissionMapper.deleteById(permissionId.getPermissionId());
    }

    @Override
    public Permission findByPermissionId(String permissionId) {
        PermissionPO permissionPO = permissionMapper.findById(permissionId).orElse(null);
        Permission permission = PermissionFactory.toDO(permissionPO);
        return permission;
    }

    @Override
    public Permission findByPermissionCode(String code) {
        PermissionPO permissionPO = permissionMapper.getByPermissionCode(code);
        Permission permission = PermissionFactory.toDO(permissionPO);
        return permission;
    }

    @Override
    public boolean existsByPermissionCode(String code) {
        return permissionMapper.existsByPermissionCode(code);
    }

    @Override
    public boolean existsByPermissionId(PermissionId id) {
        return permissionMapper.existsById(id.getPermissionId());
    }

    @Override
    public List<PermissionId> findAllPermissionIds() {
        List<String> all = permissionMapper.findAllPermissionIds();
        List<PermissionId> permissionIds = all.stream().map(id -> new PermissionId(id)).collect(Collectors.toList());
        return permissionIds;
    }

    @Override
    public List<String> findAllPermissionCodes() {
        return permissionMapper.findAllPermissionCodes();
    }

    @Override
    public List<Permission> findAll() {
        List<PermissionPO> all = permissionMapper.findAll();
        List<Permission> permissions = all.stream().map(permissionPO -> PermissionFactory.toDO(permissionPO)).collect(Collectors.toList());
        return permissions;
    }


}
