package com.innowise.employeemodule.dto.HiringEmployeeInfoDTO;

import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class HiringEmployeeCreationDTO {

    @NotNull
    private EmployeeUpdateDTO employee;

    @NotNull
    private LocalDate dateOfHiring;

}
