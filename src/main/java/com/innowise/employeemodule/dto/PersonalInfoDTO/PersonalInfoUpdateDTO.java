package com.innowise.employeemodule.dto.PersonalInfoDTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PersonalInfoUpdateDTO {

    @Id
    private Long id;

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

    @NotNull
    private String skype;

    @NotNull
    private String description;
}
