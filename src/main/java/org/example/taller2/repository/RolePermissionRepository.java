package org.example.taller2.repository;

import org.example.taller2.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
}
