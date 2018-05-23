package com.lottoland.game.service;

import com.lottoland.game.model.Shape;
import org.springframework.stereotype.Service;

@Service
public class ShapeComparator {

    public int compare(Shape shape1, Shape shape2) {
        if (shape1 == shape2) {
            return 0;
        }
        if (shape1 == Shape.PAPER && shape2 == Shape.ROCK || shape1 == Shape.ROCK && shape2 == Shape.SCISSORS || shape1 == Shape.SCISSORS && shape2 == Shape.PAPER) {
            return 1;
        }
        return -1;
    }
}