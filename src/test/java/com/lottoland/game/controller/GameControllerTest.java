package com.lottoland.game.controller;

import com.lottoland.game.model.GameResult;
import com.lottoland.game.model.Shape;
import com.lottoland.game.service.GameService;
import com.lottoland.game.service.GameSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
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
        GameResult gameResult = GameResult.builder().player1Shape(Shape.PAPER).player2Shape(Shape.SCISSORS).build();
        when(gameService.play()).thenReturn(gameResult);

        // when
        ResponseEntity<GameResult> responseEntity = gameController.playGame();

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(gameResult);

    }
}