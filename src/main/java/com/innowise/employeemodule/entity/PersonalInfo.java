package com.innowise.employeemodule.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "personal_info")
public class PersonalInfo extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", schema = "employee_schema", sequenceName = "sq_personal_info")
    private Long id;

    private String firstName;

    private String lastName;

    private String adress;

    private String email;

    private String phoneNumber;

    private String skype;

    private String description;

}
