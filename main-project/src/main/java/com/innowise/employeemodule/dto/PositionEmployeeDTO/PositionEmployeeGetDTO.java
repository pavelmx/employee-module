package com.innowise.employeemodule.dto.PositionEmployeeDTO;

import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeFullNameDTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeUpdateDTO;
import com.innowise.employeemodule.dto.PositionDTO.PositionGetDTO;
import com.innowise.employeemodule.dto.PositionDTO.PositionUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class PositionEmployeeGetDTO {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private EmployeeFullNameDTO employee;

    @NotNull
    private PositionGetDTO position;

    private String description;

    private LocalDate startDateForPosition;

    private LocalDate endDateForPosition;

}
