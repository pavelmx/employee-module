package com.innowise.employeemodule.dto.DepartmentDTO;

import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeIdDTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeUpdateDTO;
import com.innowise.employeemodule.entity.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DepartmentCreationDTO {

    @NotNull
    private String name;

    private Long manager_id;

}
