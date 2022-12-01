package com.game.tictactoe.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import javax.annotation.Resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.tictactoe.entity.Game;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.exceptions.ResourceNotFoundException;
import com.game.tictactoe.repositories.GameRepository;
import com.game.tictactoe.repositories.PlayerRepository;
import com.game.tictactoe.requests.NewGameRequest;
import com.game.tictactoe.requests.NewPlayerRequest;
import com.game.tictactoe.requests.PlayRequest;
import com.game.tictactoe.services.GameService;
import com.game.tictactoe.services.PlayerService;
import com.game.tictactoe.view.PlayerView;

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

	@MockBean
	private GameRepository gameRepo;

	@MockBean
	private PlayerRepository playerRepo;

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
	@DisplayName("play controller test ResourceNotFoundException")
	public void play_ResourceNotFoundException() throws JsonProcessingException, Exception {
		PlayRequest req = new PlayRequest();
		req.setGameId(1111L);
		Mockito.when(playerService.getById(1111L)).thenThrow( new ResourceNotFoundException("Not found player with id = 1111"));

		mockMvc.perform(put("/api/player/play/{playerId}", 1111L).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req)))
				.andExpect(status().isBadRequest())
		.andExpect(content().string("{\"message\":\"Not found player with id = 1111\",\"o\":null}"));
		

	}
	

	@Test
	@DisplayName("play exception controller test exception InternalServerError")
	public void playExceptionInternal() throws JsonProcessingException, Exception {
		PlayRequest req = new PlayRequest();
		req.setGameId(1111L);

		mockMvc.perform(put("/api/player/play/{playerId}", 1111L).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req)))
				.andExpect(status().isInternalServerError());

	}

	@Test
	@DisplayName("player with o play first exception test ")
	public void play_PlayerOFirstExcep() throws JsonProcessingException, Exception {
		Game g = new Game();
		String[][] board = { { "", "", "" }, { "", "", "" }, { "", "", "" } };
		g.setBoard(board);
		g.setChancesLeft(9);
		g.setGameOver(false);
		g.setPlayer1(1L);
		g.setPlayer2(2L);
		g.setTurn(1L);
		g.setId(3L);

		PlayRequest req = new PlayRequest();
		req.setGameId(3L);
		req.setI(0);
		req.setJ(0);

		Mockito.when(playerService.getById(any(Long.class))).thenReturn(new Player(1L, "p", "x"));

		Mockito.when(gameService.findById(any(Long.class))).thenReturn(g);

		mockMvc.perform(put("/api/player/play/{playerId}", 2L).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req)))
			    .andExpect(status().isNotAcceptable())
				.andExpect(content().string("{\"message\":\"Please wait your turn\",\"o\":null}"));
	}
	

	@Test
	@DisplayName("player with x play first Ok test ")
	public void play_PlayerXFirstOk() throws JsonProcessingException, Exception {
		Game g = new Game();
		String[][] board = { { "", "", "" }, { "", "", "" }, { "", "", "" } };
		g.setBoard(board);
		g.setChancesLeft(9);
		g.setGameOver(false);
		g.setPlayer1(1L);
		g.setPlayer2(2L);
		g.setTurn(1L);
		g.setId(3L);

		PlayRequest req = new PlayRequest();
		req.setGameId(3L);
		req.setI(0);
		req.setJ(0);

		Mockito.when(playerService.getById(any(Long.class))).thenReturn(new Player(1L, "p", "x"));

		Mockito.when(gameService.findById(any(Long.class))).thenReturn(g);

		mockMvc.perform(put("/api/player/play/{playerId}", 1L).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req)))
			    .andExpect(status().isCreated());
	}
	
	@Test
	@DisplayName("Game Over check test ")
	public void play_GameOver() throws JsonProcessingException, Exception {
		Game g = new Game();
		String[][] board = { { "", "x", "x" }, { "", "", "" }, { "", "", "" } };
		g.setBoard(board);
		g.setChancesLeft(9);
		g.setGameOver(false);
		g.setPlayer1(1L);
		g.setPlayer2(2L);
		g.setTurn(1L);
		g.setId(3L);

		PlayRequest req = new PlayRequest();
		req.setGameId(3L);
		req.setI(0);
		req.setJ(0);

		Mockito.when(playerService.getById(any(Long.class))).thenReturn(new Player(1L, "p", "x"));

		Mockito.when(gameService.findById(any(Long.class))).thenReturn(g);

		mockMvc.perform(put("/api/player/play/{playerId}", 1L).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req)))
			    .andExpect(status().isCreated());
	}

}
