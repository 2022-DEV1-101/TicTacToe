package com.game.tictactoe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author boura
 * Game entity to be used with jpa
 */
@Entity
@Table(name = "Game")
public class Game {

	@Id
	@GeneratedValue
	@Column(name = "game_id")
	private Long id;
	
	@Column(name = "player1_id")
	private Long player1;

	
	@Column(name = "player2_id")
	private Long player2;

	@Column(name = "board")
	private String[][] board;

	@Column(name = "turn")
	private Long turn;

	@Column(name = "gameOver")
	private boolean gameOver;

	@Column(name = "chancesLeft")
	private Integer chancesLeft;

	

	public Game() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getTurn() {
		return turn;
	}

	public void setTurn(Long long1) {
		this.turn = long1;
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
