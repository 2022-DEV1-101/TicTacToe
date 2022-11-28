package com.game.tictactoe.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.game.tictactoe.converterView.PlayerConverter;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.repositories.PlayerRepository;
import com.game.tictactoe.view.PlayerView;

@DataJpaTest
public class PlayerServicesTest {

	@Autowired
	private PlayerRepository playerRepository;

	private PlayerConverter playerConverter = new PlayerConverter();

	@Autowired
	private TestEntityManager entityManager;

	@Test
	@DisplayName("add new player service test")
	public void newPlayer() throws Exception {
		PlayerService playerService = new PlayerService(playerConverter, playerRepository);

		Player player = entityManager.persist(new Player("player"));
		PlayerView p = playerService.save(player);
		Assertions.assertThat(p).isNotEqualTo(null);
	}

}
