package com.game.tictactoe.view;

public class GameView {
	@SuppressWarnings("unused")
	private Long idGame;

	private PlayerView player1;

	private PlayerView player2;

	private String[][] board;

	private Long turn;

	private boolean gameOver;

	private Integer chancesLeft;


	public GameView() {
		super();
	}


	public void setId(Long id) {
		this.idGame = id;
	}

	public PlayerView getPlayer1() {
		return player1;
	}

	public void setPlayer1(PlayerView player1) {
		this.player1 = player1;
	}

	public PlayerView getPlayer2() {
		return player2;
	}

	public void setPlayer2(PlayerView player2) {
		this.player2 = player2;
	}

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}

	public Long getTurn() {
		return turn;
	}

	public void setTurn(Long turn) {
		this.turn = turn;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}


	public void setChancesLeft(Integer chancesLeft) {
		this.chancesLeft = chancesLeft;
	}
	
	
	
	
}
