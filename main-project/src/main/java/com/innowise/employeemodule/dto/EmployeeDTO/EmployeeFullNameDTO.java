package com.innowise.employeemodule.dto.EmployeeDTO;

import com.innowise.employeemodule.dto.PersonalInfoDTO.PersonalInfoFullNameDTO;
import com.innowise.employeemodule.dto.PersonalInfoDTO.PersonalInfoUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EmployeeFullNameDTO {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private PersonalInfoFullNameDTO personalInfo;
}
