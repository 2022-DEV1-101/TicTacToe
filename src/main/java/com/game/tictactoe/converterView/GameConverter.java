package com.game.tictactoe.converterView;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.game.tictactoe.entity.Game;
import com.game.tictactoe.view.GameView;
import com.game.tictactoe.view.PlayerView;

@Component
public class GameConverter implements Converter<Game, GameView> {

	@Override
	public GameView convert(Game game) {
		GameView gameView = new GameView();
		PlayerView p1 = new PlayerView();
		PlayerView p2 = new PlayerView();
		
		p1.setId(game.getPlayer1().getId());
		p1.setUserName(game.getPlayer1().getUserName());
		p1.setSymbole(game.getPlayer1().getSymbole());
		
		p2.setId(game.getPlayer2().getId());
		p2.setUserName(game.getPlayer2().getUserName());
		p2.setSymbole(game.getPlayer2().getSymbole());
		
		gameView.setId(game.getId());
		gameView.setBoard(game.getBoard());
		gameView.setChancesLeft(game.getChancesLeft());
		gameView.setGameOver(game.isGameOver());
		gameView.setPlayer1(p1);
		gameView.setPlayer2(p2);
		gameView.setTurn(game.getTurn());
		return gameView;
	}

}
