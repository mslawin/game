package com.lottoland.game.service;

import com.lottoland.game.model.GameResult;
import com.lottoland.game.model.Shape;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    private ShapeComparator shapeComparator;

    private GameService gameService;

    @Before
    public void setUp() throws Exception {
        gameService = new GameService(shapeComparator);
    }

    @Test
    public void shouldPlayer1Win() {
        // given
        when(shapeComparator.compare(any(Shape.class), any(Shape.class))).thenReturn(1);

        // when
        GameResult gameResult = gameService.play();

        // then
        assertThat(gameResult.getResult()).isEqualTo(1);
    }

    @Test
    public void shouldPlayer2Win() {
        // given
        when(shapeComparator.compare(any(Shape.class), any(Shape.class))).thenReturn(-1);

        // when
        GameResult gameResult = gameService.play();

        // then
        assertThat(gameResult.getResult()).isEqualTo(-1);
    }

    @Test
    public void shouldBeDraw() {
        // given
        when(shapeComparator.compare(any(Shape.class), any(Shape.class))).thenReturn(0);

        // when
        GameResult gameResult = gameService.play();

        // then
        assertThat(gameResult.getResult()).isEqualTo(0);
    }
}