package com.game.tictactoe.requests;

/**
 * @author boura
 * object to be received when a player play his game
 */
public class PlayRequest {

	private Long gameId;
	private Integer i;
	private Integer j;

	

	public PlayRequest() {
		super();
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public Integer getJ() {
		return j;
	}

	public void setJ(Integer j) {
		this.j = j;
	}

}
