package com.game.tictactoe.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.game.tictactoe.converterView.PlayerConverter;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.exceptions.ResourceNotFoundException;
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
	
	
	@Test
	@DisplayName("get player by id service test")
	public void getPlayerById() throws Exception {
		PlayerService playerService = new PlayerService(playerConverter, playerRepository);
		Player p1 = this.playerRepository.save(new Player("test"));
		Player pExpected = playerService.getById(p1.getId());
		Assertions.assertThat(p1).isEqualTo(pExpected);
	}
	
	@Test
	@DisplayName("get player by id , id doesn't exist, service test")
	public void getPlayerByIdException() throws Exception {
		PlayerService playerService = new PlayerService(playerConverter, playerRepository);
		assertThrows(ResourceNotFoundException.class, () -> 
		{playerService.getById(100L);});
	}

}
