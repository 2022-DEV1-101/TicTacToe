package com.game.tictactoe.exceptions;

/**
 * @author boura
 * to use when one of the rules is been not respected
 */
public class RulesNotRespectedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RulesNotRespectedException(String msg) {
		super(msg);
	}
}