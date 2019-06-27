package com.innowise.employeemodule.dto.PersonalInfoDTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PersonalInfoCreationDTO {


    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String adress;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    private String skype;

    private String description;
}
