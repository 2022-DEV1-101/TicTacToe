package com.game.tictactoe.requests;

/**
 * @author boura
 * object to be received on the end point when create new player 
 */
public class NewPlayerRequest {

	private String userName;

	private String symbole;

	

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
