package com.game.tictactoe.requests;

public class NewGameRequest {

	private Long player1;

	private Long player2;

	private String[][] board;

	private Integer turn;

	private boolean gameOver;

	private Integer chancesLeft;

	public NewGameRequest(Long player1, Long player2, String[][] board, Integer turn, boolean gameOver,
			Integer chancesLeft) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.board = board;
		this.turn = turn;
		this.gameOver = gameOver;
		this.chancesLeft = chancesLeft;
	}

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
