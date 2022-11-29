package com.game.tictactoe.converterView;

public class ResponsePlay {

	private Object o;
	private String message;

	public ResponsePlay(Object o, String message) {
		super();
		this.o = o;
		this.message = message;
	}

	public ResponsePlay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
