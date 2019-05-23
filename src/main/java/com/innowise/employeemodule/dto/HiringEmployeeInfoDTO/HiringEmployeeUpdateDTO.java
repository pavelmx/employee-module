package com.innowise.employeemodule.dto.HiringEmployeeInfoDTO;

import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class HiringEmployeeUpdateDTO {

    @Id
    private Long id;

    @NotNull
    private EmployeeUpdateDTO employee;


}
