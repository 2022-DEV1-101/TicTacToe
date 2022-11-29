package com.game.tictactoe.converterView;

import java.io.Serializable;

import com.game.tictactoe.view.GameView;

public class ResponsePlay  {

	/**
	 * 
	 */
	private GameView gameView;
	private String message;

	public ResponsePlay(GameView gameView, String message) {
		super();
		this.gameView = gameView;
		this.message = message;
	}

	public ResponsePlay() {
		super();
		// TODO Auto-generated constructor stub
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
