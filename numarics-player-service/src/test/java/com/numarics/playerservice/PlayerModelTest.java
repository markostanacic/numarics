package com.numarics.playerservice;

import com.numarics.commons.model.PlayerModel;

public class PlayerModelTest {
    public static PlayerModel playerModel() {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName("Marko");
        playerModel.setGameId(10L);

        return playerModel;
    }
}
