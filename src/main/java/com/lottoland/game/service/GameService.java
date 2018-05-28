package com.lottoland.game.service;

import com.lottoland.game.model.GameResult;
import com.lottoland.game.model.Player;
import com.lottoland.game.model.Shape;
import com.lottoland.game.model.Winner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final ShapeComparator shapeComparator;

    public GameService(ShapeComparator shapeComparator) {
        this.shapeComparator = shapeComparator;
    }

    public GameResult play() {
        Shape randomPlayerShape = Player.RANDOM.getShapeSupplier().get();
        Shape rockPlayerShape = Player.ALWAYS_ROCK.getShapeSupplier().get();

        int result = shapeComparator.compare(randomPlayerShape, rockPlayerShape);
        Winner winner;
        if (result == 1) {
            winner = Winner.RandomPlayer;
        } else if (result == -1) {
            winner = Winner.RockPlayer;
        } else {
            winner = Winner.Draw;
        }
        return GameResult.builder().randomPlayerShape(randomPlayerShape).rockPlayerShape(rockPlayerShape).winner(winner).build();
    }

    public long getRandomPlayerWins(List<GameResult> gameResults) {
        return gameResults
                .stream()
                .filter(gameResult -> gameResult.getWinner() == Winner.RandomPlayer)
                .count();
    }

    public long getRockPlayerWins(List<GameResult> gameResults) {
        return gameResults
                .stream()
                .filter(gameResult -> gameResult.getWinner() == Winner.RockPlayer)
                .count();
    }
}