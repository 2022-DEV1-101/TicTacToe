package com.game.tictactoe.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.game.tictactoe.converterView.GameConverter;
import com.game.tictactoe.converterView.ResponsePlay;
import com.game.tictactoe.entity.Game;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.exceptions.ResourceNotFoundException;
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
	@DisplayName("checkFirstStep p1 service Exception chance left < 9 test")
	public void checkFirstStepCaseChancesLessThenNineP1() {
		Game g = new Game();
		g.setChancesLeft(8);
		Player p1 = new Player();
		p1.setId(1L);
		p1.setSymbole("x");
		Player p2 = new Player();
		p2.setId(2L);
		p2.setSymbole("o");
		g.setPlayer1(p1);
		g.setPlayer2(p2);
		g.setTurn(1L);

		assertDoesNotThrow(() -> {
			this.gameService.checkFirstPlay(g, p1);
		});
	}
	
	@Test
	@DisplayName("checkFirstStep p2 service Exception chance left < 9 test")
	public void checkFirstStepCaseChancesLessThenNineP2() {
		Game g = new Game();
		g.setChancesLeft(8);
		Player p1 = new Player();
		p1.setId(1L);
		p1.setSymbole("x");
		Player p2 = new Player();
		p2.setId(2L);
		p2.setSymbole("o");
		g.setPlayer1(p1);
		g.setPlayer2(p2);
		g.setTurn(1L);

		assertDoesNotThrow(() -> {
			this.gameService.checkFirstPlay(g, p2);
		});
	}
	
	
	@Test
	@DisplayName("checkFirstStep Okservice test")
	public void checkFirstStepOk() {
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

		assertDoesNotThrow(() -> {
			this.gameService.checkFirstPlay(g, p1);
		});
	}

	@Test
	@DisplayName("findById service exception test")
	public void findByIdException() {
		assertThrows(ResourceNotFoundException.class, () -> {
			this.gameService.findById(9999L);
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
	@DisplayName("check left chance  test")
	public void checkLeftChance() {
		Game g = new Game();
		g.setGameOver(false);
		g.setChancesLeft(0);
		assertThrows(RulesNotRespectedException.class, () -> {
			this.gameService.checkGameOver(g);
		});

	}

	@Test
	@DisplayName("check left chance  test")
	public void NoGameOver() {
		Game g = new Game();
		g.setGameOver(false);
		g.setChancesLeft(1);
		assertDoesNotThrow(() -> {
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
	@DisplayName("check winner column 1 service test")
	public void winnerCol1() {
		String[][] board = { { "o", "x", "" }, { "o", "x", "" }, { "o", "x", "" } };
		String symbole = "x";
		boolean winner = this.gameService.winner(board, symbole);

		Assertions.assertThat(winner).isEqualTo(true);
	}

	@Test
	@DisplayName("check winner line 0 service test")
	public void winnerLine0() {
		String[][] board = { { "x", "x", "x" }, { "o", "o", "" }, { "", "", "" } };
		String symbole = "x";
		boolean winner = this.gameService.winner(board, symbole);
		Assertions.assertThat(winner).isEqualTo(true);
	}

	@Test
	@DisplayName("check winner line 1 service test")
	public void winnerLine1() {
		String[][] board = { { "x", "o", "x" }, { "x", "x", "x" }, { "o", "", "" } };
		String symbole = "x";
		boolean winner = this.gameService.winner(board, symbole);
		Assertions.assertThat(winner).isEqualTo(true);
	}

	@Test
	@DisplayName("check winner line 2 service test")
	public void winnerLine2() {
		String[][] board = { { "", "", "" }, { "", "o", "o" }, { "x", "x", "x" } };
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

	@Test
	@DisplayName("play Step service test: Position is unavailable")
	public void playStep_ExceptionPosition() {

		PlayRequest playReq = new PlayRequest();
		playReq.setI(0);
		playReq.setJ(1);

		ResponsePlay response = new ResponsePlay();
		Player p = new Player();
		Game game = new Game();
		String[][] board = { { "", "o", "x" }, { "o", "x", "" }, { "x", "", "" } };
		Long pNextTurn = 1L;

		Exception exception = assertThrows(RulesNotRespectedException.class, () -> {
			this.gameService.playStep(playReq, response, p, game, board, pNextTurn);
		});

		String expectedMessage = "Position is unavailable !";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	@DisplayName("play Step service test: Ok test")
	public void playStep_Position() {

		PlayRequest playReq = new PlayRequest();
		playReq.setI(0);
		playReq.setJ(0);

		ResponsePlay response = new ResponsePlay();
		Player p = new Player();
		p.setSymbole("x");
		p.setUserName("p");
		Game game = new Game();
		game.setChancesLeft(9);
		String[][] board = { { "", "", "" }, { "", "", "" }, { "", "", "" } };
		Long pNextTurn = 1L;

		this.gameService.playStep(playReq, response, p, game, board, pNextTurn);

	}

	@Test
	@DisplayName("play Step service test: winner test")
	public void playStep_winner() {

		PlayRequest playReq = new PlayRequest();
		playReq.setI(0);
		playReq.setJ(0);

		ResponsePlay response = new ResponsePlay();
		Player p = new Player();
		p.setSymbole("x");
		p.setUserName("p");
		Game game = new Game();
		game.setChancesLeft(9);
		String[][] board = { { "", "x", "x" }, { "o", "o", "" }, { "", "", "" } };
		Long pNextTurn = 1L;

		this.gameService.playStep(playReq, response, p, game, board, pNextTurn);

		String expectedMessage = "p is the winner ";
		String actualMessage = response.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
		Assertions.assertThat(game.isGameOver()).isEqualTo(true);
	}

	@Test
	@DisplayName("play Step service test: chance left test")
	public void playStep_chanceLeft() {

		PlayRequest playReq = new PlayRequest();
		playReq.setI(0);
		playReq.setJ(0);

		ResponsePlay response = new ResponsePlay();
		Player p = new Player();
		p.setSymbole("x");
		p.setUserName("p");
		Game game = new Game();
		game.setChancesLeft(0);
		String[][] board = { { "", "o", "x" }, { "o", "o", "x" }, { "x", "x", "o" } };
		Long pNextTurn = 1L;
		game.setChancesLeft(1);

		this.gameService.playStep(playReq, response, p, game, board, pNextTurn);

		String expectedMessage = "Game is a draw";
		String actualMessage = response.getMessage();
		System.out.println("------>" + actualMessage);
		assertTrue(actualMessage.contains(expectedMessage));

	}

}
