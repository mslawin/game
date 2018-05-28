package com.lottoland.game.controller;

import com.lottoland.game.model.AllGamesResults;
import com.lottoland.game.model.GameResult;
import com.lottoland.game.model.Shape;
import com.lottoland.game.model.Winner;
import com.lottoland.game.service.GameService;
import com.lottoland.game.service.GameSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @Mock
    private GameService gameService;

    @Mock
    private GameSession gameSession;

    private GameController gameController;

    @Before
    public void setUp() throws Exception {
        gameController = new GameController(gameService, gameSession);
    }

    @Test
    public void shouldReturnGameResult() {
        // given
        GameResult gameResult = GameResult.builder().randomPlayerShape(Shape.PAPER).rockPlayerShape(Shape.SCISSORS).build();
        when(gameService.play()).thenReturn(gameResult);

        // when
        ResponseEntity<GameResult> responseEntity = gameController.playGame();

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(gameResult);
    }

    @Test
    public void shouldReturn204WhenNoGames() {
        // given
        when(gameSession.getGameResults()).thenReturn(Collections.emptyList());

        // when
        ResponseEntity<AllGamesResults> responseEntity = gameController.allGamesResults();

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void shouldReturnAllGames() {
        // given
        when(gameSession.getGameResults()).thenReturn(Arrays.asList(GameResult.builder().winner(Winner.RandomPlayer).rockPlayerShape(Shape.ROCK).randomPlayerShape(Shape.PAPER).build(),
                                                                    GameResult.builder().winner(Winner.RandomPlayer).rockPlayerShape(Shape.ROCK).randomPlayerShape(Shape.PAPER).build(),
                                                                    GameResult.builder().winner(Winner.Draw).rockPlayerShape(Shape.ROCK).randomPlayerShape(Shape.ROCK).build()));
        when(gameService.getRandomPlayerWins(anyList())).thenCallRealMethod();
        when(gameService.getRockPlayerWins(anyList())).thenCallRealMethod();

        // when
        ResponseEntity<AllGamesResults> responseEntity = gameController.allGamesResults();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        AllGamesResults body = responseEntity.getBody();
        assertThat(body.getRockPlayerWins()).isEqualTo(0);
        assertThat(body.getRandomPlayerWins()).isEqualTo(2);
        assertThat(body.getDraws()).isEqualTo(1);
        assertThat(body.getGameResults()).hasSize(3);
    }
}