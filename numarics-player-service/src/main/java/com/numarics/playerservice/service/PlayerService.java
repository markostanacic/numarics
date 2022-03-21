package com.numarics.playerservice.service;

import com.numarics.commons.model.PlayerModel;
import com.numarics.commons.model.PlayerModelResponse;

import java.util.List;

public interface PlayerService {

    PlayerModelResponse registerPlayer(final PlayerModel playerModel);

    PlayerModelResponse getPlayer(Long id);

    void deletePlayer(final Long id);

    List<PlayerModelResponse> getPlayersByName(String name);
}
