package com.numarics.gameservice.model;

import com.numarics.commons.model.PlayerModelResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Getter
@Setter
public class GameModelResponse implements Serializable {

    private static final long serialVersionUID = -1024045537536479696L;
    private Long id;

    private String name;

    private GameStatus status;

    private PlayerModelResponse playerModelResponse;

}
