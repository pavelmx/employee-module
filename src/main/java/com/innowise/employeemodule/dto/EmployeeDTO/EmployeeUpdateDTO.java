package com.innowise.employeemodule.dto.EmployeeDTO;

import com.innowise.employeemodule.dto.PersonalInfoDTO.PersonalInfoUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EmployeeUpdateDTO {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private boolean isActive;

    @NotNull
    private PersonalInfoUpdateDTO personalInfo;
}
