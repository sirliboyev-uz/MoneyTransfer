package com.example.moneytransfer.Repository;

import com.example.moneytransfer.Enums.RoleName;
import com.example.moneytransfer.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleName roleName);
}
