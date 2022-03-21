package com.numarics.playerservice.service;

import com.numarics.playerservice.model.PlayerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerServiceRepository extends CrudRepository<PlayerEntity, Long> {

    List<PlayerEntity> findAllByName(String name);

}
