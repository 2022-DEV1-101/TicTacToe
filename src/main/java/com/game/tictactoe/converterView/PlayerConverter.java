package com.game.tictactoe.converterView;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.game.tictactoe.entity.Player;
import com.game.tictactoe.view.PlayerView;

@Component
public class PlayerConverter implements Converter<Player, PlayerView> {

	@Override
	public PlayerView convert(Player player) {
		PlayerView playerView = new PlayerView();
		playerView.setId(player.getId());
		playerView.setSymbole(player.getSymbole());
		playerView.setUserName(player.getUserName());
		return playerView;
	}

}
