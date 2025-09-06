package org.example.taller2.repository;

import org.example.taller2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<User, Long> {}