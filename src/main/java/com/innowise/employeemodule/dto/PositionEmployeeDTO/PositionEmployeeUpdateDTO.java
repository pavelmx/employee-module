package com.innowise.employeemodule.dto.PositionEmployeeDTO;

import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeUpdateDTO;
import com.innowise.employeemodule.dto.PositionDTO.PositionUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class PositionEmployeeUpdateDTO {

    @Id
    private Long id;

    @NotNull
    private EmployeeUpdateDTO employee;

    @NotNull
    private PositionUpdateDTO position;


}
