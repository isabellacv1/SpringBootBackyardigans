package org.example.taller2.repository;

import org.example.taller2.entity.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EventStatusRepository extends JpaRepository<EventStatus, Long> {
}
