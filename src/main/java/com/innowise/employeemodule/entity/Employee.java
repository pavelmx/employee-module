package com.innowise.employeemodule.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", schema = "employee_schema", sequenceName = "sq_employee")
    private Long id;

    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "personal_info_id")
    private PersonalInfo personalInfo;
}
