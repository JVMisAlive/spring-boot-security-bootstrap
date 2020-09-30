package com.web.service;

import com.web.model.Role;
import com.web.model.User;
import com.web.repository.RoleRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void saveRole(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepo.delete(role);
    }

    @Override
    public void updateRole(Role role) {
        roleRepo.save(role);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepo.getOne(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepo.getRoleByName(name);
    }

    @Override
    public Set<Role> getRoleSet(Set<String> roles) {
        return new HashSet<>(roleRepo.getRoleByNameIn(roles));
    }

    @Override
    public String getViewRoleSet(User user){
        return user.getRoles()
                .stream()
                .map(role -> role.getName().substring(5))
                .collect(Collectors.joining(", "));
    }

}
