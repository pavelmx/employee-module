package com.innowise.employeemodule.dto.DepartmentEmployeeDTO;

import com.innowise.employeemodule.dto.DepartmentDTO.DepartmentUpdateDTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeUpdateDTO;
import com.innowise.employeemodule.dto.PositionEmployeeDTO.PositionEmployeeUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
public class DepartmentEmployeeUpdateDTO {

    @Id
    private Long id;

    @NotNull
    private EmployeeUpdateDTO employee;

    @NotNull
    private PositionEmployeeUpdateDTO positionEmployee;

    @NotNull
    private DepartmentUpdateDTO department;

    @NotNull
    private boolean isCurrentDepartment;
}
