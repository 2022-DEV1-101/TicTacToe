package com.game.tictactoe.converterView;

import org.springframework.stereotype.Component;

import com.game.tictactoe.entity.Game;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.view.GameView;
import com.game.tictactoe.view.PlayerView;

/**
 * @author boura this class is to convert game object to gameView object for
 *         best practice
 */
@Component
public class GameConverter {

	/**
	 * Method to convert a game object to gameView object for best practice
	 * 
	 * @param game
	 * @param p1
	 * @param p2
	 * @return GameView
	 */
	public GameView convert(Game game, Player p1, Player p2) {
		GameView gameView = new GameView();
		PlayerView p1v = new PlayerView();
		PlayerView p2v = new PlayerView();

		p1v.setId(p1.getId());
		p1v.setUserName(p1.getUserName());
		p1v.setSymbole(p1.getSymbole());

		p2v.setId(p2.getId());
		p2v.setUserName(p2.getUserName());
		p2v.setSymbole(p2.getSymbole());

		gameView.setId(game.getId());
		gameView.setBoard(game.getBoard());
		gameView.setChancesLeft(game.getChancesLeft());
		gameView.setGameOver(game.isGameOver());
		gameView.setPlayer1(p1v);
		gameView.setPlayer2(p2v);
		gameView.setTurn(game.getTurn());
		return gameView;
	}

}
