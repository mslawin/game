package com.lottoland.game.controller;

import com.lottoland.game.model.AllGamesResults;
import com.lottoland.game.model.GameResult;
import com.lottoland.game.service.AllGamesResultsService;
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
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final GameSession gameSession;
    private final AllGamesResultsService allGamesResultsService;

    public GameController(GameService gameService, GameSession gameSession, AllGamesResultsService allGamesResultsService) {
        this.gameService = gameService;
        this.gameSession = gameSession;
        this.allGamesResultsService = allGamesResultsService;
    }

    @PostMapping("/user")
    public ResponseEntity<GameResult> playGame() {
        GameResult gameResult = gameService.play();
        gameSession.addResult(gameResult);
        allGamesResultsService.add(gameResult);
        return ResponseEntity.ok(gameResult);
    }

    @GetMapping("/user")
    public ResponseEntity<AllGamesResults> allGamesResultsPerUser() {
        return getAllGamesResults(gameSession.getGameResults());
    }

    @DeleteMapping("/user")
    public void reset() {
        gameSession.getGameResults().clear();
    }

    @GetMapping("/total")
    public ResponseEntity<AllGamesResults> totalGamesResults() {
        return getAllGamesResults(allGamesResultsService.getAllGamesResults());
    }

    private ResponseEntity<AllGamesResults> getAllGamesResults(List<GameResult> gameResults) {
        if (CollectionUtils.isEmpty(gameResults)) {
            return ResponseEntity.noContent().build();
        }
        long randomPlayerWins = gameService.getRandomPlayerWins(gameResults);
        long rockPlayerWins = gameService.getRockPlayerWins(gameResults);
        long draws = gameResults.size() - randomPlayerWins - rockPlayerWins;
        return ResponseEntity.ok(AllGamesResults.builder().gameResults(gameResults).randomPlayerWins(randomPlayerWins).rockPlayerWins(rockPlayerWins).draws(draws).build());
    }
}