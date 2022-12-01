package com.game.tictactoe.exceptions;

/**
 * @author boura
 *	To use when we do not find our resource
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}