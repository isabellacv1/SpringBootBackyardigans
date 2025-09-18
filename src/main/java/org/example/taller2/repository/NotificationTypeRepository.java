package org.example.taller2.repository;

import org.example.taller2.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {
}
