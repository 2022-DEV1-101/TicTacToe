package com.game.tictactoe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.tictactoe.entity.Player;
import com.game.tictactoe.requests.NewGameRequest;
import com.game.tictactoe.requests.NewPlayerRequest;
import com.game.tictactoe.services.GameService;
import com.game.tictactoe.services.PlayerService;
import com.game.tictactoe.view.GameView;
import com.game.tictactoe.view.PlayerView;

@RestController
@RequestMapping(path = "/api")
public class TicTacToeController {
	private final PlayerService playerService;
	private final GameService gameService;

	public TicTacToeController(PlayerService playerSerice, GameService gameService) {
		this.playerService = playerSerice;
		this.gameService = gameService;
	}

	@PostMapping(value = "/player/new")
	@ResponseBody
	public ResponseEntity<PlayerView> newPlayer(@RequestBody NewPlayerRequest playerReq) {
		Player player = new Player(playerReq.getUserName());
		return new ResponseEntity<>(this.playerService.save(player), HttpStatus.CREATED);
	}

	@PostMapping(value = "/game/new")
	@ResponseBody
	public ResponseEntity<GameView> newGame(@RequestBody NewGameRequest gameReq) {

		Player p1 = this.playerService.getById(gameReq.getPlayer1());
		Player p2 = this.playerService.getById(gameReq.getPlayer2());
		return new ResponseEntity<>(this.gameService.InitializeGame(gameReq, p1, p2), HttpStatus.CREATED);
	}
}
