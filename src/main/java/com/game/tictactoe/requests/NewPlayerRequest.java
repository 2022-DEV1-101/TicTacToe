package com.game.tictactoe.requests;

public class NewPlayerRequest {

	private String userName;

	private String symbole;

	public NewPlayerRequest(Integer id, String userName, String symbole) {
		super();
		this.userName = userName;
		this.symbole = symbole;
	}

	public NewPlayerRequest() {
		super();
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
