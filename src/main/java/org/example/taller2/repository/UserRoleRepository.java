package org.example.taller2.repository;

import org.example.taller2.entity.UserRole;
import org.example.taller2.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}