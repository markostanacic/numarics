package com.numarics.playerservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.numarics.commons.model.PlayerModelResponse;
import com.numarics.playerservice.PlayerModelTest;
import com.numarics.playerservice.PlayerTest;
import com.numarics.playerservice.model.PlayerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)

class PlayerServiceTest {

    @MockBean
    private PlayerServiceRepository playerServiceRepository;

    @Autowired
    private PlayerService playerService;

    @Test
    void createNewPlayer() {
        when(playerServiceRepository.save(any(PlayerEntity.class))).thenReturn(PlayerTest.playerEntity());

        PlayerModelResponse playerModelResponse = playerService.registerPlayer(PlayerModelTest.playerModel());

        assertThat(playerModelResponse.getName()).isEqualTo("Marko");
        assertThat(playerModelResponse.getGameId()).isEqualTo(10L);
    }

    @Test
    void getPlayerById() {
        when(playerServiceRepository.findById(anyLong())).thenReturn(Optional.of(PlayerTest.playerEntity()));

        PlayerModelResponse playerModelResponse = playerService.getPlayer(2L);

        assertThat(playerModelResponse.getName()).isEqualTo("Marko");
        assertThat(playerModelResponse.getGameId()).isEqualTo(10L);
    }

}
