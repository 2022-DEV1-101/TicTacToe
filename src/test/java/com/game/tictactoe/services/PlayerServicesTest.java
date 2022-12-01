package com.game.tictactoe.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

	@Mock
	private PlayerRepository playerRepositoryMock;

	@InjectMocks
	private PlayerService PlayerServiceMock;

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
		Assertions.assertThat(pExpected).isEqualTo(p1);
		Assertions.assertThat(pExpected).isInstanceOf(Player.class);
	}

	
	

	@Test
	@DisplayName("getPlayerIdException test")
	public void getPlayerIdExceptionn() {
		Player p = new Player("p");
		entityManager.persist(p);
		when(playerRepositoryMock.findById(2L)).thenReturn(Optional.empty());
		ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
			PlayerServiceMock.getById(p.getId());

		});
		// PlayerServiceMock.getById(p.getId()));
		Assertions.assertThat(ex.getMessage()).isEqualTo("Not found player with id = " + p.getId());

	}

	@Test
	@DisplayName("getPlayerIdException test")
	public void getPlayerIdAssertNoException() {
		Player p = new Player("p");
		entityManager.persist(p);
		when(playerRepositoryMock.findById(p.getId())).thenReturn(Optional.empty());
		assertDoesNotThrow(() -> {
			playerRepositoryMock.findById(p.getId()).isPresent();

		});

	}

	@Test
	@DisplayName("getPlayerIdException test")
	public void getPlayerIdNoException() {
		when(playerRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new Player("p")));
		Assertions.assertThat(PlayerServiceMock.getById(anyLong())).isNotNull();
		Assertions.assertThat(PlayerServiceMock.getById(anyLong()).getUserName()).isEqualTo("p");
	}


}
