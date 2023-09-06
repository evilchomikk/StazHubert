package com.example.SpringStart.tables.role.repository;

import com.example.SpringStart.tables.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
