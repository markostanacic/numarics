package com.numarics.playerservice.endpoint;

import com.numarics.playerservice.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.numarics.commons.model.PlayerModel;
import com.numarics.commons.model.PlayerModelResponse;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/player")
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceEndpoint {
    private final PlayerService playerService;

    @PostMapping(path = "/register")
    public PlayerModelResponse registerPlayer(@RequestBody @Valid PlayerModel playerModel) {
        return playerService.registerPlayer(playerModel);
    }

    @GetMapping(path = "/{id}")
    public PlayerModelResponse getPlayer(@PathVariable Long id) {
        return playerService.getPlayer(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);

        log.info("record deleted!");
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<PlayerModelResponse> getPlayers(@RequestParam String name) {
        return playerService.getPlayersByName(name);
    }
}
