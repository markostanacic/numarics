package com.numarics.gameservice.endpoint;

import com.numarics.commons.model.SearchCriteria;
import com.numarics.gameservice.model.GameModel;
import com.numarics.gameservice.model.GameModelResponse;
import com.numarics.gameservice.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/game")
@RequiredArgsConstructor
@Slf4j
public class GameServiceEndpoint {
    private final GameService gameService;

    @PostMapping(path = "/play")
    public GameModelResponse playGame(@RequestBody @Valid GameModel gameModel) {
        return gameService.playGame(gameModel);
    }

    @PutMapping(path = "/play")
    public GameModelResponse updateGame(@RequestBody @Valid GameModel gameModel) {
        return gameService.updateGame(gameModel);
    }

    @GetMapping(path = "/{id}")
    public GameModelResponse getGame(@PathVariable Long id) {
        return gameService.getGame(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);

        log.info("record deleted!");
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/search")
    public List<GameModelResponse> search(@RequestParam String gameName, @RequestParam String gameStatus,
                                          @RequestParam String playerName) {

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setGameName(gameName);
        searchCriteria.setGameStatus(gameStatus);
        searchCriteria.setPlayerName(playerName);


        return gameService.search(searchCriteria);
    }

}
