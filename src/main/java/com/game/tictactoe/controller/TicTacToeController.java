package com.game.tictactoe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.tictactoe.converterView.ResponsePlay;
import com.game.tictactoe.entity.Game;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.exceptions.ResourceNotFoundException;
import com.game.tictactoe.exceptions.RulesNotRespectedException;
import com.game.tictactoe.requests.NewGameRequest;
import com.game.tictactoe.requests.NewPlayerRequest;
import com.game.tictactoe.requests.PlayRequest;
import com.game.tictactoe.services.GameService;
import com.game.tictactoe.services.PlayerService;
import com.game.tictactoe.view.GameView;
import com.game.tictactoe.view.PlayerView;

/**
 * @author bouraoui
 * This rest controller contains all the end point of this app
 * it will manage 3 possible case : 
 * -create player
 * -create new game
 * -play action 
 */
@RestController
@RequestMapping(path = "/api")
public class TicTacToeController {
	private final PlayerService playerService;
	private final GameService gameService;

	public TicTacToeController(PlayerService playerSerice, GameService gameService) {
		this.playerService = playerSerice;
		this.gameService = gameService;
	}

	/**
	 * this endpoint to create new player by calling post https method
	 * ../api/player/new
	 * 
	 * @param playerReq
	 * @return ResponseEntity<PlayerView>
	 */
	@PostMapping(value = "/player/new")
	@ResponseBody
	public ResponseEntity<PlayerView> newPlayer(@RequestBody NewPlayerRequest playerReq) {
		Player player = new Player(playerReq.getUserName());
		return new ResponseEntity<>(this.playerService.save(player), HttpStatus.CREATED);
	}

	/**
	 * this endpoint to create new game by calling post https method ../api/game/new
	 * which mean to initilize the game
	 * 
	 * @param gameReq
	 * @return ResponseEntity<GameView>
	 */
	@PostMapping(value = "/game/new")
	@ResponseBody
	public ResponseEntity<GameView> newGame(@RequestBody NewGameRequest gameReq) {

		Player p1 = this.playerService.getById(gameReq.getPlayer1());
		Player p2 = this.playerService.getById(gameReq.getPlayer2());
		return new ResponseEntity<>(this.gameService.InitializeGame(gameReq, p1, p2), HttpStatus.CREATED);
	}

	/**
	 * this endpoint will manage the game after been initialized, it make sure that the
	 * rules are been respected X always goes first. Players cannot play on a played
	 * position. Players alternate placing X’s and O’s on the board until either:
	 * One player has three in a row, horizontally, vertically or diagonally All
	 * nine squares are filled. If a player is able to draw three x’s or three o’s
	 * in a row, that player wins. If all nine squares are filled and neither player
	 * has three in a row, the game is a draw.
	 * 
	 * @param playReq
	 * @param playerId
	 * @return ResponseEntity<ResponsePlay>
	 */
	@PutMapping(value = "/player/play/{playerId}")
	@ResponseBody
	public ResponseEntity<ResponsePlay> play(@RequestBody PlayRequest playReq, @PathVariable Long playerId) {
		ResponsePlay response = new ResponsePlay();
		try {
			Player p = this.playerService.getById(playerId);
			Game game = this.gameService.findById(playReq.getGameId());
			String[][] board = game.getBoard();

			this.gameService.checkFirstPlay(game, p);

			this.gameService.checkGameOver(game);

			Long pNextTurn = this.gameService.getNextPlayerId(game, playerId);

			if (game.getTurn().equals(playerId)) {
				this.gameService.playStep(playReq, response, p, game, board, pNextTurn);
			} else {
				throw new RulesNotRespectedException("Please wait your turn");
			}
		} catch (RulesNotRespectedException e) {
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		} catch (ResourceNotFoundException e) {
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
