package com.lottoland.game.model;

import java.util.Random;
import java.util.function.Supplier;

public enum Player {

    RANDOM(Player::getRandomShape),
    ALWAYS_ROCK(() -> Shape.ROCK);

    private final Supplier<Shape> shapeSupplier;

    Player(Supplier<Shape> shapeSupplier) {
        this.shapeSupplier = shapeSupplier;
    }

    public Supplier<Shape> getShapeSupplier() {
        return shapeSupplier;
    }

    private static Shape getRandomShape() {
        return Shape.class.getEnumConstants()[new Random().nextInt(Shape.class.getEnumConstants().length)];
    }
}