package com.game.tictactoe.services;

import org.springframework.stereotype.Service;

import com.game.tictactoe.converterView.GameConverter;
import com.game.tictactoe.converterView.ResponsePlay;
import com.game.tictactoe.entity.Game;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.exceptions.ResourceNotFoundException;
import com.game.tictactoe.exceptions.RulesNotRespectedException;
import com.game.tictactoe.repositories.GameRepository;
import com.game.tictactoe.repositories.PlayerRepository;
import com.game.tictactoe.requests.NewGameRequest;
import com.game.tictactoe.requests.PlayRequest;
import com.game.tictactoe.view.GameView;

/**
 * @author boura will contains all the services needed for the game
 */
@Service
public class GameService {
	private final GameRepository gameRepo;
	private final GameConverter gameConverter;
	private final PlayerRepository playerRepo;

	public GameService(GameRepository gameRepo, GameConverter gameConverter, PlayerRepository playerRepo) {
		this.gameConverter = gameConverter;
		this.gameRepo = gameRepo;
		this.playerRepo = playerRepo;
	}

	/**
	 * method to initialize the game
	 * 
	 * @param gameReq
	 * @param p1
	 * @param p2
	 * @return GameView
	 */
	public GameView InitializeGame(NewGameRequest gameReq, Player p1, Player p2) {
		Game game = new Game();
		String[][] board = { { "", "", "" }, { "", "", "" }, { "", "", "" } };
		game.setBoard(board);
		game.setGameOver(false);
		game.setChancesLeft(9);
		game.setTurn(p1.getId());
		game.setPlayer1(p1.getId());
		game.setPlayer2(p2.getId());
		Game gToConv = this.gameRepo.saveAndFlush(game);
		p1.setSymbole("x");
		p2.setSymbole("o");
		this.playerRepo.save(p1);
		this.playerRepo.save(p2);
		GameView gameView = convertToView(gToConv, p1, p2);
		return gameView;
	}

	/**
	 * search game by id if not exist through an exception
	 * 
	 * @param id
	 * @return Game
	 */
	public Game findById(Long id) {
		return this.gameRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found game with id = " + id));
	}

	/**
	 * save/update a game
	 * 
	 * @param game
	 * @return GameView
	 */
	public GameView save(Game game) {
		Game g = this.gameRepo.saveAndFlush(game);
		Player p1 = this.playerRepo.getOne(game.getPlayer1());
		Player p2 = this.playerRepo.getOne(game.getPlayer2());
		return convertToView(g, p1, p2);
	}

	/**
	 * convert a game to gameView
	 * 
	 * @param game
	 * @param p1
	 * @param p2
	 * @return GameView
	 */
	public GameView convertToView(Game game, Player p1, Player p2) {
		return this.gameConverter.convert(game, p1, p2);
	}

	/**
	 * managing the turn between the two player
	 * 
	 * @param g
	 * @param id
	 * @return
	 */
	public Long getNextPlayerId(Game g, Long id) {
		if (g.getPlayer1().equals(id)) {
			return g.getPlayer2();
		} else {
			return g.getPlayer1();
		}
	}

	/**
	 * to check if the first play have 'x' as symbole
	 * 
	 * @param g
	 * @param p
	 */
	public void checkFirstPlay(Game g, Player p) {
		if (g.getChancesLeft() == 9 && p.getSymbole().equals("o"))
			throw new RulesNotRespectedException("Player with symbole 'x' must start first");

	}

	/**
	 * to check if the game is over or not
	 * 
	 * @param game
	 */
	public void checkGameOver(Game game) {
		if (game.isGameOver() || game.getChancesLeft() == 0)
			throw new RulesNotRespectedException("Game finished already !");
	}

	/**
	 * check availabilty of a case in the board
	 * 
	 * @param board
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkAvailability(String[][] board, int x, int y) {
		return board[x][y].equals("");

	}

	/**
	 * to put the played action and make change on the game
	 * 
	 * @param board
	 * @param pNextTurn
	 * @param playReq
	 * @param g
	 * @param p
	 */
	public void play(String[][] board, Long pNextTurn, PlayRequest playReq, Game g, Player p) {
		board[playReq.getI()][playReq.getJ()] = p.getSymbole();
		g.setBoard(board);
		g.setChancesLeft(g.getChancesLeft() - 1);
		g.setTurn(pNextTurn);

	}

	/**
	 * check the winner , by fetching all the possible cases, example : If a player
	 * with x symbole have three x’s in a row return true
	 * 
	 * @param board
	 * @param symbole
	 * @return
	 */
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

	/**
	 * this method will contain the whole logic of the a player to play on his turn
	 * result will be saved under response
	 * 
	 * @param playReq
	 * @param response
	 * @param p
	 * @param game
	 * @param board
	 * @param pNextTurn
	 */
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
