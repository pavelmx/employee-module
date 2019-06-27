package com.innowise.employeemodule.entity.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.PrimitiveIterator;

@Getter
@Setter
public class EmployeeFilter {

    private String firstName;
    private String lastName;
    private String adress;
    private String skype;
    private String phoneNumber;
    private String email;
    private String description;
    private String active;
    private String positionId;
    private String departmentId;
}
