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
public class SearchCriteria implements Serializable {

    private static final long serialVersionUID = 4367961730020309741L;

    @NotNull
    @NotEmpty
    private String gameStatus;

    @NotNull
    @NotEmpty
    private String gameName;

    @NotNull
    @NotEmpty
    private String playerName;
}
