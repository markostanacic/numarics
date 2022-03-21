package com.numarics.playerservice.service;

import com.numarics.commons.model.PlayerModel;
import com.numarics.commons.model.PlayerModelResponse;
import com.numarics.commons.model.RecordNotFoundException;
import com.numarics.playerservice.model.PlayerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerServiceRepository playerServiceRepository;

    public PlayerModelResponse registerPlayer(final PlayerModel playerModel) {
        Assert.notNull(playerModel, "Model must not be null!");

        return mapToDto(playerServiceRepository.save(mapToEntity(playerModel)));
    }

    public PlayerModelResponse getPlayer(Long id) {
        Assert.notNull(id, "Id must not be null!");
        return mapToDto(playerServiceRepository.findById(id).orElseThrow(RecordNotFoundException::new));
    }

    public void deletePlayer(final Long id) {
        Assert.notNull(id, "Id must not be null!");

        PlayerEntity playerEntity = playerServiceRepository.findById(id).orElseThrow(RecordNotFoundException::new);

        playerServiceRepository.delete(playerEntity);

    }

    private PlayerModelResponse mapToDto(PlayerEntity playerEntity) {
        PlayerModelResponse playerModelResponse = new PlayerModelResponse();
        playerModelResponse.setId(playerEntity.getId());
        playerModelResponse.setName(playerEntity.getName());
        playerModelResponse.setGameId(playerEntity.getGameId());

        return playerModelResponse;
    }

    private PlayerEntity mapToEntity(PlayerModel playerModel) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName(playerModel.getName());
        playerEntity.setGameId(playerModel.getGameId());

        return playerEntity;
    }

    public List<PlayerModelResponse> getPlayersByName(String name) {
        Assert.notNull(name, "Name must not be null!");
        List<PlayerEntity> playerEntities = playerServiceRepository.findAllByName(name);

        return playerEntities.stream().map(this::mapToDto).collect(Collectors.toList());

    }
}
