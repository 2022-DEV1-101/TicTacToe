package com.game.tictactoe.services;

import org.springframework.stereotype.Service;

import com.game.tictactoe.converterView.GameConverter;
import com.game.tictactoe.entity.Game;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.exceptions.ResourceNotFoundException;
import com.game.tictactoe.repositories.GameRepository;
import com.game.tictactoe.requests.NewGameRequest;
import com.game.tictactoe.requests.PlayRequest;
import com.game.tictactoe.view.GameView;

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
		game.setTurn(p1.getId());
		p1.setSymbole("x");
		p2.setSymbole("o");
		game.setPlayer1(p1);
		game.setPlayer2(p2);
		GameView gameView = convertToView(this.gameRepo.save(game));
		return gameView;
	}
	
	public Game findById(Long id) {
		return this.gameRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found game with id = " + id));
	}
	
	public GameView save(Game game) {
		Game g = this.gameRepo.save(game);
		return convertToView(g);
	}
	
	public GameView convertToView(Game game) {
		return this.gameConverter.convert(game);
	}

	public Long getNextPlayerId(Game g, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void checkFirstPlay(Game g, Player p2) {
		// TODO Auto-generated method stub
		
	}

	public void checkGameOver(Game g) {
		// TODO Auto-generated method stub
		
	}

	public void checkAvailability(Game g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void play(String[][] board, Long pNextTurn, PlayRequest playReq, Game g, Player p) {
		// TODO Auto-generated method stub
		
	}

	public boolean winner(String[][] board, String symbole) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}
