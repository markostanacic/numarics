package com.numarics.playerservice;

import com.numarics.commons.model.PlayerModelResponse;

public class PlayerModelResponseTest {
    public static PlayerModelResponse playerModelResponse() {
        PlayerModelResponse playerModelResponse = new PlayerModelResponse();
        playerModelResponse.setId(1L);
        playerModelResponse.setName("marko");
        playerModelResponse.setGameId(10L);
        return playerModelResponse;
    }
}
