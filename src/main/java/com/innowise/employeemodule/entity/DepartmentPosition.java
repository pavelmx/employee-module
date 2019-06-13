package com.innowise.employeemodule.entity;
import lombok.*;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class DepartmentPosition extends AbstractEntity {

    private Long id;

    private Employee employee;

    private Department department;

    private Position position;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
