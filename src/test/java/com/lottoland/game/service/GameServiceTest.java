package com.lottoland.game.service;

import com.lottoland.game.model.GameResult;
import com.lottoland.game.model.Shape;
import com.lottoland.game.model.Winner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

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
        assertThat(gameResult.getWinner()).isEqualTo(Winner.RandomPlayer);
    }

    @Test
    public void shouldPlayer2Win() {
        // given
        when(shapeComparator.compare(any(Shape.class), any(Shape.class))).thenReturn(-1);

        // when
        GameResult gameResult = gameService.play();

        // then
        assertThat(gameResult.getWinner()).isEqualTo(Winner.RockPlayer);
    }

    @Test
    public void shouldBeDraw() {
        // given
        when(shapeComparator.compare(any(Shape.class), any(Shape.class))).thenReturn(0);

        // when
        GameResult gameResult = gameService.play();

        // then
        assertThat(gameResult.getWinner()).isEqualTo(Winner.Draw);
    }

    @Test
    public void shouldReturnRandomPlayerZeroWinsWhenNoGames() {

        // when
        long randomPlayerWins = gameService.getRandomPlayerWins(Collections.emptyList());

        // then
        assertThat(randomPlayerWins).isEqualTo(0);
    }

    @Test
    public void shouldReturnRandomPlayerZeroWinsWhenOnlyRockPlayerWins() {

        // when
        long randomPlayerWins = gameService.getRandomPlayerWins(Collections.singletonList(GameResult.builder().winner(Winner.RockPlayer).build()));

        // then
        assertThat(randomPlayerWins).isEqualTo(0);
    }

    @Test
    public void shouldReturnRandomPlayerOneWin() {

        // when
        long randomPlayerWins = gameService.getRandomPlayerWins(Arrays.asList(GameResult.builder().winner(Winner.RandomPlayer).build(), GameResult.builder().winner(Winner.RockPlayer).build()));

        // then
        assertThat(randomPlayerWins).isEqualTo(1);
    }
}