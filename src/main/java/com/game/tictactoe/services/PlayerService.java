package com.game.tictactoe.services;

import org.springframework.stereotype.Service;

import com.game.tictactoe.converterView.PlayerConverter;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.repositories.PlayerRepository;
import com.game.tictactoe.view.PlayerView;

@Service
public class PlayerService {

	public PlayerService(PlayerConverter playerConverter, PlayerRepository playerRepository) {
		// TODO Auto-generated constructor stub
	}

	public PlayerView save(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

}
