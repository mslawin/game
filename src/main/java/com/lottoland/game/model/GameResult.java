package com.lottoland.game.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GameResult {

    private final Shape player1Shape;
    private final Shape player2Shape;
    private final int result;
}