package com.main.service.impl;

import com.main.entity.Roles;
import com.main.repository.RoleRepository;
import com.main.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Roles> findAllRoles() {
        return roleRepository.findAll();
    }
}
