package com.game.tictactoe.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.game.tictactoe.converterView.GameConverter;
import com.game.tictactoe.entity.Game;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.exceptions.RulesNotRespectedException;
import com.game.tictactoe.repositories.GameRepository;
import com.game.tictactoe.requests.PlayRequest;
import com.game.tictactoe.view.GameView;

@DataJpaTest
public class GameServicesTest {
	@Autowired
	private GameRepository gameRepository;

	private GameConverter gameConverter = new GameConverter();
	private GameService gameService = new GameService(gameRepository, gameConverter);

	@Test
	@DisplayName("add new game service test")
	public void newGame() throws Exception {

		Game game = new Game();
		String[][] board = { { "", "", "" }, { "", "", "" }, { "", "", "" } };
		game.setBoard(board);
		game.setGameOver(false);
		game.setChancesLeft(9);
		Player p1 = new Player();
		game.setTurn(p1.getId());
		Player p2 = new Player();
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
	@DisplayName("checkPossibilityLeft service test")
	public void checkPossibilityLeft() {
		Game g = new Game();
		g.setChancesLeft(0);
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
		assertThrows(RulesNotRespectedException.class, () -> {
			this.gameService.checkAvailability(g, x, y);
		});

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
		String[][] board = { { "x", "x", "x" }, { "o", "o", "" }, { "", "", "" } };
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
