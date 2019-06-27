package com.innowise.employeemodule.dto.DepartmentEmployeeDTO;

import com.innowise.employeemodule.dto.DepartmentDTO.DepartmentGetWitoutManagerDTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeIdDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class DepartmentEmployeeGetCurrentDTO {

    @NotNull
    private EmployeeIdDTO employee;

    @NotNull
    private DepartmentGetWitoutManagerDTO department;

}
