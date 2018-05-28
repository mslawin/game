package com.lottoland.game.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AllGamesResults {

    private final List<GameResult> gameResults;
    private final long randomPlayerWins;
    private final long rockPlayerWins;
    private final long draws;
}