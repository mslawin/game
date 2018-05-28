package com.lottoland.game.controller;

import com.lottoland.game.model.GameResult;
import com.lottoland.game.service.GameSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GameControllerSessionTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldSaveGameResultInSession() throws Exception {
        // given
        MockHttpSession session = new MockHttpSession();

        // when
        mockMvc.perform(post("/game").session(session));

        // then
        GameSession gameSession = (GameSession) session.getAttribute("scopedTarget.gameSession");
        assertThat(gameSession.getGameResults()).hasSize(1);
    }

    @Test
    public void shouldResetGames() throws Exception {
        // given
        GameSession gameSession = new GameSession();
        gameSession.addResult(GameResult.builder().build());
        gameSession.addResult(GameResult.builder().build());
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("scopedTarget.gameSession", gameSession);

        // when
        mockMvc.perform(post("/game/reset").session(session));

        // then
        gameSession = (GameSession) session.getAttribute("scopedTarget.gameSession");
        assertThat(gameSession.getGameResults()).hasSize(0);
    }
}