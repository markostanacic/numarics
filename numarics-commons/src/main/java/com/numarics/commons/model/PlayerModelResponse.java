package com.numarics.commons.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Getter
@Setter
public class PlayerModelResponse implements Serializable {

    private static final long serialVersionUID = 3142039104049673954L;

    private Long id;

    private Long gameId;

    private String name;

}
