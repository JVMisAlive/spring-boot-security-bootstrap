package com.web.service;

import com.web.model.Role;

import java.util.List;

public interface RoleService {
    void saveRole(Role role);

    void deleteRole(Role role);

    void updateRole(Role role);

    Role getRoleById(Long id);

    List<Role> getAllRoles();

    Role getRoleByName(String name);
}
