package com.numarics.gameservice.model;

import com.numarics.commons.model.PlayerModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
@Getter
@Setter
public class GameModel implements Serializable {

    private static final long serialVersionUID = -4147154490280091815L;

    private Long id;

    @NotNull(message = "name cannot be null!")
    @NotEmpty(message = "name cannot be empty!")
    private String name;

    private GameStatus status;

    private PlayerModel playerModel;
}
