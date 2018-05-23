package com.lottoland.game.service;

import com.lottoland.game.model.Shape;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShapeComparatorTest {

    private final ShapeComparator shapeComparator = new ShapeComparator();

    @Test
    public void paperShouldBeatRock() {

        // when
        int result = shapeComparator.compare(Shape.PAPER, Shape.ROCK);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void rockShouldBeatScissors() {

        // when
        int result = shapeComparator.compare(Shape.ROCK, Shape.SCISSORS);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void scissorsShouldBeatPaper() {

        // when
        int result = shapeComparator.compare(Shape.SCISSORS, Shape.PAPER);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void paperShouldLooseWithScissors() {

        // when
        int result = shapeComparator.compare(Shape.PAPER, Shape.SCISSORS);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void rockShouldLooseWithPaper() {

        // when
        int result = shapeComparator.compare(Shape.ROCK, Shape.PAPER);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void scissorsShouldLooseWithRock() {

        // when
        int result = shapeComparator.compare(Shape.SCISSORS, Shape.ROCK);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void paperShouldDrawWithPaper() {

        // when
        int result = shapeComparator.compare(Shape.PAPER, Shape.PAPER);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void rockShouldDrawWithRock() {

        // when
        int result = shapeComparator.compare(Shape.ROCK, Shape.ROCK);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void scissorsShouldDrawWithScissors() {

        // when
        int result = shapeComparator.compare(Shape.SCISSORS, Shape.SCISSORS);

        // then
        assertThat(result).isEqualTo(0);
    }
}