package com.game.tictactoe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Game")
public class Game {

	@Id
	@GeneratedValue
	@Column(name = "game_id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "player_id")
	private Player player1;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "player_id")
	private Player player2;

	@Column(name = "board")
	private String[][] board;

	@Column(name = "turn")
	private Integer turn;

	@Column(name = "gameOver")
	private boolean gameOver;

	@Column(name = "chancesLeft")
	private Integer chancesLeft;

	public Game(Player player1, Player player2, String[][] board, Integer turn, boolean gameOver, Integer chancesLeft) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.board = board;
		this.turn = turn;
		this.gameOver = gameOver;
		this.chancesLeft = chancesLeft;
	}

	public Game() {
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
