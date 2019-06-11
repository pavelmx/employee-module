package com.innowise.employeemodule.entity.candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCandidate {

    private String firstName;

    private String lastName;

    private boolean deleted;

    private DepartmentCandidate department;

}