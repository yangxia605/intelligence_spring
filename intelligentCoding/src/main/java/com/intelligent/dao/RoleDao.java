package com.intelligent.dao;

import com.intelligent.model.Roles;
import com.intelligent.type.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Roles, Integer> {
    Roles findByName(RoleName roleName);
}
