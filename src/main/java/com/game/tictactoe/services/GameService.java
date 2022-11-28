package com.game.tictactoe.services;

import org.springframework.stereotype.Service;

import com.game.tictactoe.converterView.GameConverter;
import com.game.tictactoe.entity.Game;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.repositories.GameRepository;
import com.game.tictactoe.requests.NewGameRequest;
import com.game.tictactoe.view.GameView;
import com.game.tictactoe.view.PlayerView;

@Service
public class GameService {
	private final GameRepository gameRepo;
	private final GameConverter gameConverter;

	public GameService(GameRepository gameRepo, GameConverter gameConverter) {
		this.gameConverter = gameConverter;
		this.gameRepo = gameRepo;
	}

	public GameView InitializeGame(NewGameRequest gameReq, Player p1, Player p2) {
		Game game = new Game();
		String[][] board = { { "", "", "" }, { "", "", "" }, { "", "", "" } };
		game.setBoard(board);
		game.setGameOver(false);
		game.setChancesLeft(9);
		game.setTurn(1);
		p1.setSymbole("x");
		p2.setSymbole("o");
		game.setPlayer1(p1);
		game.setPlayer2(p2);
		GameView gameView = convertToView(this.gameRepo.save(game));
		return gameView;
	}
	
	public GameView convertToView(Game game) {
		return this.gameConverter.convert(game);
	}
	
	
}
