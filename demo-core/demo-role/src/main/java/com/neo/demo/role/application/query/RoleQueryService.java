package com.neo.demo.role.application.query;

import com.neo.demo.role.domain.model.aggregate.Role;
import com.neo.demo.role.domain.model.aggregate.RoleId;
import com.neo.demo.role.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleQueryService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByRoleId(String roleId) {
        return roleRepository.findByRoleId(roleId);
    }

    public Role findByRoleCode(String code) {
        return roleRepository.findByRoleCode(code);
    }

    public boolean existsByRoleId(RoleId id) {
        return roleRepository.existsByRoleId(id);
    }

    public boolean existsByRoleCode(String code) {
        return roleRepository.existsByRoleCode(code);
    }

    public List<RoleId> findAllRoleIds() {
        return roleRepository.findAllRoleIds();
    }

    public List<String> findAllRoleCodes() {
        return roleRepository.findAllRoleCodes();
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }


}
