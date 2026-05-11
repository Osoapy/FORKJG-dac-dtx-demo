package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxRepository
        extends JpaRepository<OutboxEvent, String> {

    List<OutboxEvent> findByStatus(String status);
}