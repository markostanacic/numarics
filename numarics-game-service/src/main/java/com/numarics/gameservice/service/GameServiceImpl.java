package com.numarics.gameservice.service;

import com.numarics.commons.model.PlayerModel;
import com.numarics.commons.model.PlayerModelResponse;
import com.numarics.commons.model.RecordNotFoundException;
import com.numarics.commons.model.SearchCriteria;
import com.numarics.gameservice.model.GameEntity;
import com.numarics.gameservice.model.GameModel;
import com.numarics.gameservice.model.GameModelResponse;
import com.numarics.gameservice.model.GameStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Component
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {

    private final GameServiceRepository gameServiceRepository;

    private final RestTemplate restTemplate;

    @Value("${player.service.url}")
    private String playerServiceUrl;

    public GameModelResponse playGame(final GameModel gameModel) {
        Assert.notNull(gameModel, "Model must not be null!");
        Assert.notNull(gameModel.getPlayerModel(), "Player Model must not be null!");

        Long playerId = gameModel.getPlayerModel().getId() != null ? gameModel.getPlayerModel().getId() : null;

        GameModelResponse gameModelResponse = mapToDto(gameServiceRepository.save(mapToEntity(gameModel)));

        PlayerModelResponse playerModelResponse;

        // we check if user is registered or not and then proceeding to next action
        try {
            playerModelResponse = restTemplate.getForObject(playerServiceUrl + playerId, PlayerModelResponse.class);
        } catch (HttpClientErrorException e) {
            PlayerModel playerModel = new PlayerModel();
            playerModel.setName(gameModel.getPlayerModel().getName());
            playerModel.setGameId(gameModelResponse.getId());
            playerModelResponse = restTemplate.postForObject(playerServiceUrl + "register",
                                                             playerModel,
                                                             PlayerModelResponse.class);
        }

        gameModelResponse.setPlayerModelResponse(playerModelResponse);

        return gameModelResponse;

    }

    public GameModelResponse getGame(final Long id) {
        Assert.notNull(id, "Id must not be null!");
        return mapToDto(gameServiceRepository.findById(id).orElseThrow(RecordNotFoundException::new));
    }

    public GameModelResponse updateGame(final GameModel gameModel) {
        Assert.notNull(gameModel.getId(), "Id must not be null!");
        Assert.notNull(gameModel.getStatus(), "Status must not be null!");

        validateStatus(gameModel.getStatus());

        GameEntity gameEntity = gameServiceRepository.findById(gameModel.getId())
                                                     .orElseThrow(RecordNotFoundException::new);

        gameEntity.setStatus(gameModel.getStatus());

        return mapToDto(gameServiceRepository.save(gameEntity));
    }

    public void deleteGame(final Long id) {
        Assert.notNull(id, "Id must not be null!");

        GameEntity gameEntity = gameServiceRepository.findById(id).orElseThrow(RecordNotFoundException::new);

        gameServiceRepository.delete(gameEntity);
    }

    public List<GameModelResponse> search(final SearchCriteria searchCriteria) {
        List<GameEntity> gameEntityList = gameServiceRepository.findAllByNameAndStatus(searchCriteria.getGameName(),
                                                                                       GameStatus.valueOf(searchCriteria
                                                                                                                  .getGameStatus()));

        List<GameModelResponse> gameModelResponseList = gameEntityList.stream()
                                                                      .map(this::mapToDto)
                                                                      .collect(Collectors.toList());

        PlayerModelResponse[] playerModelResponseArray = restTemplate.getForObject(
                playerServiceUrl
                        + "?name="
                        + searchCriteria.getPlayerName(),
                PlayerModelResponse[].class);

        if (playerModelResponseArray != null) {

            List<PlayerModelResponse> playerModelResponseList = Arrays.asList(playerModelResponseArray);

            return gameModelResponseList.stream()
                                        .filter(gameModelResponse -> playerModelResponseList.stream()
                                                                                            .anyMatch(
                                                                                                    playerModelResponse -> gameModelResponse
                                                                                                            .getId()
                                                                                                            .equals(playerModelResponse
                                                                                                                            .getGameId())))
                                        .collect(Collectors.toList());
        }

        return gameModelResponseList;
    }

    private GameModelResponse mapToDto(GameEntity gameEntity) {
        GameModelResponse gameModelResponse = new GameModelResponse();
        gameModelResponse.setId(gameEntity.getId());
        gameModelResponse.setName(gameEntity.getName());
        gameModelResponse.setStatus(gameEntity.getStatus());

        return gameModelResponse;
    }

    private GameEntity mapToEntity(GameModel gameModel) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setName(gameModel.getName());
        gameEntity.setStatus(GameStatus.NEW);

        return gameEntity;
    }

    private void validateStatus(GameStatus status) {
        List<GameStatus> statusList = Arrays.asList(GameStatus.values());

        if (!statusList.contains(status)) {
            throw new RecordNotFoundException("Status doesn't exits!");
        }
    }
}
