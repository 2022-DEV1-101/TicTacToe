package com.game.tictactoe.converterView;

import com.game.tictactoe.view.GameView;

/**
 * @author boura This class will represent the response of the end point
 *         api/player/play/ gameView : contains the object to show message: if
 *         we have message to display as game over, or wait your turn
 */
public class ResponsePlay {

	private GameView gameView;
	private String message;

	public ResponsePlay() {
		super();
	}

	public GameView getO() {
		return gameView;
	}

	public void setO(GameView gameView) {
		this.gameView = gameView;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
