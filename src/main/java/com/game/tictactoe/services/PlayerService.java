package com.game.tictactoe.services;

import org.springframework.stereotype.Service;

import com.game.tictactoe.converterView.PlayerConverter;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.exceptions.ResourceNotFoundException;
import com.game.tictactoe.repositories.PlayerRepository;
import com.game.tictactoe.view.PlayerView;

/**
 * @author boura contain all the services of a player
 */
@Service
public class PlayerService {

	private final PlayerRepository playerRepo;
	private final PlayerConverter playerConverter;

	public PlayerService(PlayerConverter playerConverter, PlayerRepository playerRepo) {
		this.playerConverter = playerConverter;
		this.playerRepo = playerRepo;
	}

	/**
	 * to save/update a player on the db and return a PlayerView
	 * 
	 * @param player
	 * @return PlayerView
	 */
	public PlayerView save(Player player) {
		Player p = this.playerRepo.save(player);
		return convertToView(p);
	}

	/**
	 * to search by id for a player on db if not found exception will be thrown
	 * ResourceNotFoundException
	 * 
	 * @param id
	 * @return Player
	 */
	public Player getById(Long id) {
		return this.playerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found player with id = " + id));
	}

	/**
	 * will convert a player to playerView
	 * 
	 * @param player
	 * @return
	 */
	public PlayerView convertToView(Player player) {
		return this.playerConverter.convert(player);
	}

}
