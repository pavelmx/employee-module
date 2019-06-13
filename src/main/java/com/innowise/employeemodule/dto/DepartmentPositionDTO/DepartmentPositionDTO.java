package com.innowise.employeemodule.dto.DepartmentPositionDTO;

import com.innowise.employeemodule.dto.DepartmentDTO.DepartmentGetWitoutManagerDTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeFullNameDTO;
import com.innowise.employeemodule.dto.PositionDTO.PositionGetDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
public class DepartmentPositionDTO {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private EmployeeFullNameDTO employee;

    @NotNull
    private PositionGetDTO position;

    @NotNull
    private DepartmentGetWitoutManagerDTO department;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;
}
