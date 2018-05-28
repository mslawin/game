package com.lottoland.game.service;

import com.lottoland.game.model.GameResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AllGamesResultsService {

    private final List<GameResult> allGamesResults = Collections.synchronizedList(new ArrayList<>());

    public void add(GameResult gameResult) {
        allGamesResults.add(gameResult);
    }

    public List<GameResult> getAllGamesResults() {
        return allGamesResults;
    }
}