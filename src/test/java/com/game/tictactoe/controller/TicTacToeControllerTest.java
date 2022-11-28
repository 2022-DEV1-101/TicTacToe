package com.game.tictactoe.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.tictactoe.exceptions.ResourceNotFoundException;
import com.game.tictactoe.requests.NewGameRequest;
import com.game.tictactoe.requests.NewPlayerRequest;
import com.game.tictactoe.services.GameService;
import com.game.tictactoe.services.PlayerService;

@WebMvcTest(TicTacToeController.class)
public class TicTacToeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PlayerService playerService;
	
	@MockBean
	private GameService gameService;

	@Test
	@DisplayName("new Player api test")
	public void newPlayer() throws JsonProcessingException, Exception {
		NewPlayerRequest req = new NewPlayerRequest();
		req.setUserName("player");

		mockMvc.perform(post("/api/player/new").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))).andExpect(status().isCreated());
	}

	@Test
	@DisplayName("new Game api test")
	public void newGame() throws JsonProcessingException, Exception {
		NewGameRequest req = new NewGameRequest();
		req.setBoard(null);
		req.setChancesLeft(null);
		req.setGameOver(false);
		req.setPlayer1(1L);
		req.setPlayer2(2L);
		req.setTurn(null);

		mockMvc.perform(post("/api/game/new").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))).andExpect(status().isCreated());
	}

	@Test
	@DisplayName("new Game api player 1 doesn't exist  test")
	public void newGamePlayer1Null() throws JsonProcessingException, Exception {
		NewGameRequest req = new NewGameRequest();
		req.setPlayer1(10000L);
		req.setPlayer2(1L);
		mockMvc.perform(post("/api/game/new").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req)))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
	}

	@Test
	@DisplayName("new Game api player 2 doesn't exist  test")
	public void newGamePlayer2Null() throws JsonProcessingException, Exception {
		NewGameRequest req = new NewGameRequest();
		req.setPlayer2(10000L);
		req.setPlayer1(1L);
		mockMvc.perform(post("/api/game/new").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req)))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
	}
}
