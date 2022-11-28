package com.game.tictactoe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.tictactoe.entity.Player;
import com.game.tictactoe.requests.NewPlayerRequest;
import com.game.tictactoe.services.PlayerService;
import com.game.tictactoe.view.PlayerView;

@RestController
@RequestMapping(path = "/api")
public class TicTacToeController {
	private final PlayerService playerService;

	public TicTacToeController(PlayerService playerSerice) {
		this.playerService = playerSerice;
	}

	@PostMapping(value = "/player/new")
	@ResponseBody
	public ResponseEntity<PlayerView> newPlayer(@RequestBody NewPlayerRequest playerReq) {
		Player player = new Player(playerReq.getUserName());
		return new ResponseEntity<>(this.playerService.save(player), HttpStatus.CREATED);
	}
}
