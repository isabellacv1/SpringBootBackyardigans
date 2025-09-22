package org.example.taller2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthCheckController {
    @GetMapping("/")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.status(200).body(
                Map.of("status", "UP")
        );
    }
}
