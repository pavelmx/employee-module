package com.innowise.employeemodule.dto.EmployeeDTO;

import com.innowise.employeemodule.dto.PersonalInfoDTO.PersonalInfoCreationDTO;
import com.innowise.employeemodule.dto.PersonalInfoDTO.PersonalInfoUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EmployeeCreationDTO {

    @NotNull
    private PersonalInfoCreationDTO personalInfo;
}
