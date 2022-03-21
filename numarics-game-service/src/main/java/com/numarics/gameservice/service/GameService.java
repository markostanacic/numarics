package com.numarics.gameservice.service;

import com.numarics.commons.model.SearchCriteria;
import com.numarics.gameservice.model.GameModel;
import com.numarics.gameservice.model.GameModelResponse;

import java.util.List;

public interface GameService {

    GameModelResponse playGame(final GameModel gameModel);

    GameModelResponse getGame(final Long id);

    GameModelResponse updateGame(final GameModel gameModel);

    void deleteGame(final Long id);

    List<GameModelResponse> search(final SearchCriteria searchCriteria);
}
