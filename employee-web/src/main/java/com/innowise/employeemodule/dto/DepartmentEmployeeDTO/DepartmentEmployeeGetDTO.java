package com.innowise.employeemodule.dto.DepartmentEmployeeDTO;

import com.innowise.employeemodule.dto.DepartmentDTO.DepartmentGetWitoutManagerDTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeFullNameDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
public class DepartmentEmployeeGetDTO {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private EmployeeFullNameDTO employee;

    @NotNull
    private DepartmentGetWitoutManagerDTO department;

    private String description;

    private LocalDate startDateInDepartment;

    private LocalDate endDateInDepartment;

    @NotNull
    private boolean isCurrentDepartment;
}
