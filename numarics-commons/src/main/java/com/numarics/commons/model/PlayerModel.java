package com.numarics.commons.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
@Getter
@Setter
public class PlayerModel implements Serializable {

    private static final long serialVersionUID = -3201335383688797070L;

    private Long id;

    private Long gameId;

    @NotNull(message = "name cannot be null!")
    @NotEmpty(message = "name cannot be empty!")
    private String name;

}
