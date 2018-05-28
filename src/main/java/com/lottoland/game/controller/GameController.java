package com.lottoland.game.controller;

import com.lottoland.game.model.AllGamesResults;
import com.lottoland.game.model.GameResult;
import com.lottoland.game.service.GameService;
import com.lottoland.game.service.GameSession;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class GameController {

    private final GameService gameService;
    private final GameSession gameSession;

    public GameController(GameService gameService, GameSession gameSession) {
        this.gameService = gameService;
        this.gameSession = gameSession;
    }

    @PostMapping
    public ResponseEntity<GameResult> playGame() {
        GameResult gameResult = gameService.play();
        gameSession.addResult(gameResult);
        return ResponseEntity.ok(gameResult);
    }

    @GetMapping
    public ResponseEntity<AllGamesResults> allGamesResultsPerUser() {
        List<GameResult> gameResults = gameSession.getGameResults();
        if (CollectionUtils.isEmpty(gameResults)) {
            return ResponseEntity.noContent().build();
        }
        long randomPlayerWins = gameService.getRandomPlayerWins(gameResults);
        long rockPlayerWins = gameService.getRockPlayerWins(gameResults);
        long draws = gameResults.size() - randomPlayerWins - rockPlayerWins;
        return ResponseEntity.ok(AllGamesResults.builder().gameResults(gameResults).randomPlayerWins(randomPlayerWins).rockPlayerWins(rockPlayerWins).draws(draws).build());
    }

    @DeleteMapping
    public void reset() {
        gameSession.getGameResults().clear();
    }
}