package com.game.tictactoe.view;

public class PlayerView {
	private Long id;
	
	private String userName;
	
	private String symbole;
	
	

	public PlayerView() {
		super();
	}

	public PlayerView(Long id, String userName, String symbole) {
		super();
		this.id = id;
		this.userName = userName;
		this.symbole = symbole;
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
