package com.game.tictactoe.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.game.tictactoe.converterView.GameConverter;
import com.game.tictactoe.entity.Game;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.exceptions.RulesNotRespectedException;
import com.game.tictactoe.repositories.GameRepository;
import com.game.tictactoe.repositories.PlayerRepository;
import com.game.tictactoe.requests.NewGameRequest;
import com.game.tictactoe.requests.PlayRequest;
import com.game.tictactoe.view.GameView;

@DataJpaTest
public class GameServicesTest {

	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private TestEntityManager entityManager;

	@InjectMocks
	private GameService gameService;

	@Mock
	private GameConverter gameConverter;

	@Mock
	private GameRepository gameRepository;

	@Test
	@DisplayName("add new game service test")
	public void newGame() throws Exception {

		NewGameRequest newGameRequest = initnewGameRequest();

		Player p1 = entityManager.persist(new Player());
		p1.setUserName("p1");

		GameView gameResult = gameService.InitializeGame(newGameRequest, playerRepository.save(p1),
				playerRepository.save(p1));
		Assertions.assertThat(gameResult).isNull();
	}

	private NewGameRequest initnewGameRequest() {
		NewGameRequest gameExpected = new NewGameRequest();
		gameExpected.setBoard(null);
		gameExpected.setGameOver(false);
		gameExpected.setChancesLeft(9);
		gameExpected.setGameOver(false);
		return gameExpected;
	}

	@Test
	@DisplayName("checkNextPlayerId service test")
	public void checkNextPlayerId() {
		Game g = new Game();
		Player p1 = new Player();
		p1.setId(1L);
		Player p2 = new Player();
		p2.setId(2L);

		g.setPlayer1(p1);
		g.setPlayer2(p2);
		g.setTurn(p1.getId());

		Long idNextTurn = this.gameService.getNextPlayerId(g, p1.getId());
		Assertions.assertThat(idNextTurn).isEqualTo(p2.getId());
	}

	@Test
	@DisplayName("checkFirstStep service test")
	public void checkFirstStep() {
		Game g = new Game();
		g.setChancesLeft(9);
		Player p1 = new Player();
		p1.setId(1L);
		p1.setSymbole("x");
		Player p2 = new Player();
		p2.setId(2L);
		p2.setSymbole("o");
		g.setPlayer1(p1);
		g.setPlayer2(p2);
		g.setTurn(1L);

		assertThrows(RulesNotRespectedException.class, () -> {
			this.gameService.checkFirstPlay(g, p2);
		});
	}

	@Test
	@DisplayName("checkGameOver service test")
	public void checkGameOver() {
		Game g = new Game();
		g.setGameOver(true);
		assertThrows(RulesNotRespectedException.class, () -> {
			this.gameService.checkGameOver(g);
		});

	}

	@Test
	@DisplayName("checkCaseAvailability service test")
	public void checkCaseAvailability() {
		Game g = new Game();
		String[][] board = { { "", "", "" }, { "", "", "" }, { "", "", "" } };
		g.setBoard(board);
		int x = 1;
		int y = 1;
		Assertions.assertThat(this.gameService.checkAvailability(g.getBoard(), x, y) == true);
	}

	@Test
	@DisplayName("play service test")
	public void play() {
		Long pNextTurn = 2L;
		PlayRequest playReq = new PlayRequest();
		playReq.setI(0);
		playReq.setJ(0);
		Game g = new Game();
		g.setTurn(pNextTurn);
		g.setChancesLeft(null);
		g.setChancesLeft(3);
		Player p = new Player();
		p.setSymbole("x");
		String[][] board = { { "", "", "" }, { "", "", "" }, { "", "", "" } };
		g.setBoard(board);
		String[][] boardEx = { { "x", "", "" }, { "", "", "" }, { "", "", "" } };
		this.gameService.play(board, pNextTurn, playReq, g, p);

		Assertions.assertThat(g.getBoard()).isEqualTo(boardEx);
		Assertions.assertThat(g.getTurn()).isEqualTo(pNextTurn);
		Assertions.assertThat(g.getChancesLeft()).isEqualTo(2);

	}

	@Test
	@DisplayName("check winner column 0 service test")
	public void winnerCol0() {
		String[][] board = { { "x", "o", "" }, { "x", "o", "" }, { "x", "", "" } };
		String symbole = "x";
		boolean winner = this.gameService.winner(board, symbole);

		Assertions.assertThat(winner).isEqualTo(true);

	}

	@Test
	@DisplayName("check winner line 0 service test")
	public void winnerLine0() {
		String[][] board = { { "x", "x", "x" }, { "x", "o", "" }, { "x", "", "" } };
		System.out.println("------->size:" + board.length);
		String symbole = "x";
		boolean winner = this.gameService.winner(board, symbole);
		Assertions.assertThat(winner).isEqualTo(true);
	}

	@Test
	@DisplayName("check winner diag service test")
	public void winnerDiag() {
		String[][] board = { { "x", "o", "" }, { "o", "x", "" }, { "", "", "x" } };
		String symbole = "x";
		boolean winner = this.gameService.winner(board, symbole);

		Assertions.assertThat(winner).isEqualTo(true);

	}

	@Test
	@DisplayName("check winner Opposite diag service test")
	public void winnerOpDiag() {
		String[][] board = { { "", "o", "x" }, { "o", "x", "" }, { "x", "", "" } };
		String symbole = "x";
		boolean winner = this.gameService.winner(board, symbole);

		Assertions.assertThat(winner).isEqualTo(true);
	}
}
