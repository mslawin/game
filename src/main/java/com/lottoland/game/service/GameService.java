package com.lottoland.game.service;

import com.lottoland.game.model.GameResult;
import com.lottoland.game.model.Player;
import com.lottoland.game.model.Shape;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final ShapeComparator shapeComparator;

    public GameService(ShapeComparator shapeComparator) {
        this.shapeComparator = shapeComparator;
    }

    public GameResult play() {
        Shape player1shape = Player.RANDOM.getShapeSupplier().get();
        Shape player2shape = Player.ALWAYS_ROCK.getShapeSupplier().get();

        int result = shapeComparator.compare(player1shape, player2shape);
        return GameResult.builder().player1Shape(player1shape).player2Shape(player2shape).result(result).build();
    }
}