package com.game.tictactoe.view;

public class PlayerView {
	private Long idPlayer;
	
	private String userName;
	
	private String symbole;
	
	

	public PlayerView() {
		super();
	}


	public Long getId() {
		return idPlayer;
	}

	public void setId(Long id) {
		this.idPlayer = id;
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
