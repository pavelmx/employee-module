package com.innowise.employeemodule.dto.DepartmentDTO;

import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeFullNameDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DepartmentGetWitoutManagerDTO {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private String name;


}
