package com.game.tictactoe.exceptions;

public class RulesNotRespectedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RulesNotRespectedException(String msg) {
		super(msg);
	}
}