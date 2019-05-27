package com.innowise.employeemodule.dto.PersonalInfoDTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PersonalInfoGetDTO {

    @Id
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;


}
