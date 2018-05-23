package com.lottoland.game.service;

import com.lottoland.game.model.GameResult;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GameSession {
    private final List<GameResult> gameResults = new ArrayList<>();

    public void addResult(GameResult gameResult) {
        gameResults.add(gameResult);
    }

    public List<GameResult> getGameResults() {
        return gameResults;
    }
}
