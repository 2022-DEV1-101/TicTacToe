package com.game.tictactoe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NonNull;

/**
 * @author boura
 * Player entity to be used with jpa
 */
@Entity
@Table(name = "Player")
public class Player {

	@Id
	@GeneratedValue
	@Column(name = "player_id")
	private Long id;

	@NonNull
	@Column(name = "user_name")
	private String userName;

	@Column(name = "symbole")
	private String symbole;
	


	public Player(Long id, @NonNull String userName, String symbole) {
		super();
		this.id = id;
		this.userName = userName;
		this.symbole = symbole;
	}



	public Player(@NonNull String userName) {
		super();
		this.userName = userName;
	}

	public Player() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSymbole() {
		return symbole;
	}

	public void setSymbole(String symbole) {
		this.symbole = symbole;
	}

}
