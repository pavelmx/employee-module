package com.innowise.employeemodule.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "position",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "name" })})
public class Position extends AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", schema = "employee_schema", sequenceName = "sq_position")
    private Long id;

    private String name;

    private boolean isActive;

}
