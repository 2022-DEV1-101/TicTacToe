package com.game.tictactoe.controller;

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
import com.game.tictactoe.requests.NewPlayerRequest;
import com.game.tictactoe.services.PlayerService;

@WebMvcTest(TicTacToeController.class)
public class TicTacToeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PlayerService playerService;

	@Test
	@DisplayName("new Player api test")
	public void newPlayer() throws JsonProcessingException, Exception {
		NewPlayerRequest req = new NewPlayerRequest(); 
		req.setUserName("player");

		mockMvc.perform(post("/api/player/new")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(req)))
				.andExpect(status().isCreated());
	}
}
