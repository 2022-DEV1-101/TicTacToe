package com.game.tictactoe.services;

import org.springframework.stereotype.Service;

import com.game.tictactoe.converterView.GameConverter;
import com.game.tictactoe.converterView.ResponsePlay;
import com.game.tictactoe.entity.Game;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.exceptions.ResourceNotFoundException;
import com.game.tictactoe.exceptions.RulesNotRespectedException;
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
		Game gToConv = this.gameRepo.saveAndFlush(game);
		GameView gameView = convertToView(gToConv);
		return gameView;
	}

	public Game findById(Long id) {
		return this.gameRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found game with id = " + id));
	}

	public GameView save(Game game) {
		Game g = this.gameRepo.saveAndFlush(game);
		return convertToView(g);
	}

	public GameView convertToView(Game game) {
		return this.gameConverter.convert(game);
	}

	public Long getNextPlayerId(Game g, Long id) {
		if (g.getPlayer1().getId().equals(id)) {
			return g.getPlayer2().getId();
		} else {
			return g.getPlayer1().getId();
		}
	}

	public void checkFirstPlay(Game g, Player p) {
		if (g.getChancesLeft() == 9 && p.getSymbole().equals("o"))
			throw new RulesNotRespectedException("Player with symbole 'x' must start first");

	}

	public void checkGameOver(Game game) {
		if (game.isGameOver() || game.getChancesLeft() == 0)
			throw new RulesNotRespectedException("Game finished already !");
	}

	public boolean checkAvailability(String[][] board, int x, int y) {
		return board[x][y].equals("");

	}

	public void play(String[][] board, Long pNextTurn, PlayRequest playReq, Game g, Player p) {
		board[playReq.getI()][playReq.getJ()] = p.getSymbole();
		g.setBoard(board);
		g.setChancesLeft(g.getChancesLeft() - 1);
		g.setTurn(pNextTurn);

	}

	public boolean winner(String[][] board, String symbole) {
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			if (board[i][i].equals(symbole)) {
				count++;
			}
		}
		if (count == board.length)
			return true;

		count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[j][i].equals(symbole)) {
					count++;
				}
				if (count == board.length)
					return true;

			}
			count = 0;
		}

		count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j].equals(symbole)) {
					count++;
				}
				if (count == board.length)
					return true;
			}
			count = 0;
		}

		count = 0;
		for (int i = 0; i < board.length; i++) {
			if (board[i][board.length - i - 1].equals(symbole)) {
				count++;
			}
		}
		if (count == board.length)
			return true;

		return false;
	}

	public void playStep(PlayRequest playReq, ResponsePlay response, Player p, Game game, String[][] board,
			Long pNextTurn) {
		if (!checkAvailability(board, playReq.getI(), playReq.getJ())) {
			throw new RulesNotRespectedException("Position is unavailable !");
		} else {
			play(board, pNextTurn, playReq, game, p);
		}

		if (winner(game.getBoard(), p.getSymbole())) {
			game.setGameOver(true);
			response.setMessage(p.getUserName() + " is the winner ");
		}

		if (game.getChancesLeft() == 0) {
			game.setGameOver(true);
			response.setMessage("Game is a draw");
		}

		response.setO(save(game));
	}

}
