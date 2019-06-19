package com.innowise.employeemodule.entity;

import java.time.LocalDateTime;

public interface BaseEntity {
    Long getId();
    LocalDateTime getCreated();
    LocalDateTime getUpdated();
}
