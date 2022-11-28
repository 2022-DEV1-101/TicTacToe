package com.game.tictactoe.converterView;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.game.tictactoe.entity.Game;
import com.game.tictactoe.view.GameView;

@Component
public class GameConverter implements Converter<Game, GameView> {

	@Override
	public GameView convert(Game game) {
		GameView gameView = new GameView();
		gameView.setId(game.getId());
		gameView.setBoard(game.getBoard());
		gameView.setChancesLeft(game.getChancesLeft());
		gameView.setGameOver(game.isGameOver());
		gameView.setPlayer1(game.getPlayer1());
		gameView.setPlayer2(game.getPlayer2());
		gameView.setTurn(game.getTurn());
		return gameView;
	}

}
