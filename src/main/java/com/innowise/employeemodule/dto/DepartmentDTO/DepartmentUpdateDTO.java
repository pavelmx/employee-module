package com.innowise.employeemodule.dto.DepartmentDTO;

import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeUpdateDTO;
import com.innowise.employeemodule.entity.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DepartmentUpdateDTO {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private EmployeeUpdateDTO manager;

}
