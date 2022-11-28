package com.game.tictactoe.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.game.tictactoe.converterView.GameConverter;
import com.game.tictactoe.entity.Game;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.repositories.GameRepository;
import com.game.tictactoe.view.GameView;

@DataJpaTest
public class GameServicesTest {
	@Autowired
	private GameRepository gameRepository;

	private GameConverter gameConverter = new GameConverter();

	@Test
	@DisplayName("add new game service test")
	public void newGame() throws Exception {
		GameService gameService = new GameService(gameRepository, gameConverter);
		Game game = new Game();
		String[][] board = { { "", "", "" }, { "", "", "" }, { "", "", "" } };
		game.setBoard(board);
		game.setGameOver(false);
		game.setChancesLeft(9);
		game.setTurn(1);
		Player p1= new Player();
		Player p2= new Player();
		p1.setSymbole("x");
		p2.setSymbole("o");
		game.setPlayer1(p1);
		game.setPlayer2(p2);
		GameView gameView = gameService.convertToView(this.gameRepository.save(game));
		Assertions.assertThat(gameView).isNotEqualTo(null);
		Assertions.assertThat(gameView.getChancesLeft()).isEqualTo(9);
		Assertions.assertThat(gameView.getTurn()).isEqualTo(1);
		Assertions.assertThat(gameView.getPlayer1().getSymbole()).isEqualTo("x");
		Assertions.assertThat(gameView.getPlayer2().getSymbole()).isEqualTo("o");
	}
}
