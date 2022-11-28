package com.game.tictactoe.view;

import com.game.tictactoe.entity.Player;

public class GameView {
	private Long id;

	private Player player1;

	private Player player2;

	private String[][] board;

	private Integer turn;

	private boolean gameOver;

	private Integer chancesLeft;

	public GameView(Long id, Player player1, Player player2, String[][] board, Integer turn, boolean gameOver,
			Integer chancesLeft) {
		super();
		this.id = id;
		this.player1 = player1;
		this.player2 = player2;
		this.board = board;
		this.turn = turn;
		this.gameOver = gameOver;
		this.chancesLeft = chancesLeft;
	}

	public GameView() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
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
