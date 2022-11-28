package com.game.tictactoe.services;

import org.springframework.stereotype.Service;

import com.game.tictactoe.converterView.PlayerConverter;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.repositories.PlayerRepository;
import com.game.tictactoe.view.PlayerView;

@Service
public class PlayerService {

	
	private final PlayerRepository playerRepo;
	private final PlayerConverter playerConverter;

	public PlayerService(PlayerConverter playerConverter, PlayerRepository playerRepo) {
		this.playerConverter = playerConverter;
		this.playerRepo = playerRepo;
	}

	public PlayerView save(Player player) {
		Player p = this.playerRepo.save(player);
		return convertToView(p);
	}
	
	public PlayerView convertToView(Player player) {
		return this.playerConverter.convert(player);
	}

}
