package com.web.repository;

import com.web.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepo extends JpaRepository <Role, Long>{
    Role getRoleByName(String name);

    Set<Role> getRoleByNameIn(Set<String> set);
}
