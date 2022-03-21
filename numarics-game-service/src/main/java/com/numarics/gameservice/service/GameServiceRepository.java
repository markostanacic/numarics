package com.numarics.gameservice.service;

import com.numarics.gameservice.model.GameEntity;
import com.numarics.gameservice.model.GameStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameServiceRepository extends CrudRepository<GameEntity, Long> {

    List<GameEntity> findAllByNameAndStatus(String name, GameStatus status);

}
