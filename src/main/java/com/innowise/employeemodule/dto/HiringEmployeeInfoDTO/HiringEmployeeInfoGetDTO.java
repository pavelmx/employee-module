package com.innowise.employeemodule.dto.HiringEmployeeInfoDTO;

import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeFullNameDTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class HiringEmployeeInfoGetDTO {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private EmployeeFullNameDTO employee;

    @NotNull
    private LocalDate dateOfHiring;

    @NotNull
    private LocalDate dateOfDismissal;
    }
