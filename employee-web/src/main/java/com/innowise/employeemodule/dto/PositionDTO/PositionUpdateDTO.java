package com.innowise.employeemodule.dto.PositionDTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class PositionUpdateDTO {

    @Id
    private Long id;

    @NotNull
    private String name;

}
