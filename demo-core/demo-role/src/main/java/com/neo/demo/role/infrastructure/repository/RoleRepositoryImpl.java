package com.neo.demo.role.infrastructure.repository;

import cn.hutool.core.lang.Assert;
import com.neo.demo.role.domain.model.aggregate.Role;
import com.neo.demo.role.domain.model.aggregate.RoleId;
import com.neo.demo.role.domain.repository.RoleRepository;
import com.neo.demo.role.infrastructure.repository.factory.RoleFactory;
import com.neo.demo.role.infrastructure.repository.mapper.RoleMapper;
import com.neo.demo.role.infrastructure.repository.po.RolePO;
import com.neo.health.common.exception.RoleFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional
    public void save(Role role) throws RoleFailureException {
        RolePO rolePO = RoleFactory.toPO(role);
        Assert.notNull(rolePO, () -> new RoleFailureException("参数不允许为null"));
        roleMapper.save(rolePO);
    }

    @Override
    @Transactional
    public void deleteByRoleId(RoleId roleId) throws RoleFailureException {
        Assert.notNull(roleId, () -> new RoleFailureException("参数不允许为null"));
        roleMapper.deleteById(roleId.getRoleId());
    }

    @Override
    public Role findByRoleId(String roleId) {
        RolePO rolePO = roleMapper.findById(roleId).orElse(null);
        Role role = RoleFactory.toDo(rolePO);
        return role;
    }

    @Override
    public Role findByRoleCode(String code) {
        RolePO rolePO = roleMapper.getByRoleCode(code);
        Role role = RoleFactory.toDo(rolePO);
        return role;
    }

    @Override
    public boolean existsByRoleId(RoleId id) {
        return roleMapper.existsById(id.getRoleId());
    }

    @Override
    public boolean existsByRoleCode(String code) {
        return roleMapper.existsByRoleCode(code);
    }

    @Override
    public List<RoleId> findAllRoleIds() {
        List<String> roleIdList = roleMapper.findAllRoleIds();
        List<RoleId> roleIds = roleIdList.stream().map(id -> new RoleId(id)).collect(Collectors.toList());
        return roleIds;
    }

    @Override
    public List<String> findAllRoleCodes() {
        return roleMapper.findAllRoleCodes();
    }

    @Override
    public List<Role> findAll() {
        List<RolePO> all = roleMapper.findAll();
        List<Role> roles = all.stream().map(rolePO -> RoleFactory.toDo(rolePO)).collect(Collectors.toList());
        return roles;
    }

}
