package com.numarics.playerservice;

import com.numarics.playerservice.model.PlayerEntity;

public class PlayerTest {
    public static PlayerEntity playerEntity() {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(1000L);
        playerEntity.setName("Marko");
        playerEntity.setGameId(10L);

        return playerEntity;
    }
}
