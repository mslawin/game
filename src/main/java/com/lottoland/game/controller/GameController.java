package com.lottoland.game.controller;

import com.lottoland.game.model.GameResult;
import com.lottoland.game.service.GameService;
import com.lottoland.game.service.GameSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameService gameService;
    private final GameSession gameSession;

    public GameController(GameService gameService, GameSession gameSession) {
        this.gameService = gameService;
        this.gameSession = gameSession;
    }

    @PostMapping("/play")
    public ResponseEntity<GameResult> playGame() {
        GameResult gameResult = gameService.play();
        gameSession.addResult(gameResult);
        return ResponseEntity.ok(gameResult);
    }
}
