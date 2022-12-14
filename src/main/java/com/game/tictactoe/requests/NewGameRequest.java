package com.game.tictactoe.requests;

/**
 * @author boura
 *	Object to be received on the end point of create new game
 */
public class NewGameRequest {

	private Long player1;

	private Long player2;

	private String[][] board;

	private Integer turn;

	private boolean gameOver;

	private Integer chancesLeft;


	public NewGameRequest() {
		super();
	}

	public Long getPlayer1() {
		return player1;
	}

	public void setPlayer1(Long player1) {
		this.player1 = player1;
	}

	public Long getPlayer2() {
		return player2;
	}

	public void setPlayer2(Long player2) {
		this.player2 = player2;
	}

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}

	public Integer getTurn() {
		return turn;
	}

	public void setTurn(Integer turn) {
		this.turn = turn;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public Integer getChancesLeft() {
		return chancesLeft;
	}

	public void setChancesLeft(Integer chancesLeft) {
		this.chancesLeft = chancesLeft;
	}

}
