package com.game.tictactoe.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.game.tictactoe.converterView.PlayerConverter;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.exceptions.ResourceNotFoundException;
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

	public Player getById(Long id) {
		return this.playerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found player with id = " + id));

	}

	public PlayerView convertToView(Player player) {
		return this.playerConverter.convert(player);
	}

}
