package org.example.taller2.repository;

import org.example.taller2.entity.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
}
