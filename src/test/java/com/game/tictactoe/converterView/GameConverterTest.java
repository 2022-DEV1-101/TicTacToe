package com.game.tictactoe.converterView;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.game.tictactoe.entity.Game;
import com.game.tictactoe.entity.Player;
import com.game.tictactoe.view.GameView;
import com.game.tictactoe.view.PlayerView;

@DataJpaTest
public class GameConverterTest {

	private GameConverter gameConverter = new GameConverter();

	@Test
	@DisplayName("convert Game to GameView test")
	public void gameToView() {
		Game g = new Game();
		Player p1 = new Player();
		Player p2 = new Player();
		String[][] board = { { "", "", "" }, { "", "", "" }, { "", "", "" } };

		p1.setUserName("p1");
		p2.setUserName("p2");
		g.setPlayer1(1L);
		g.setPlayer2(2L);
		g.setBoard(board);
		g.setTurn(1L);
		g.setGameOver(false);

		// Expected result
		PlayerView p1View = new PlayerView();
		PlayerView p2View = new PlayerView();
		p1View.setUserName("p1");
		p2View.setUserName("p2");
		GameView gExpected = new GameView();
		gExpected.setBoard(board);
		gExpected.setGameOver(false);
		gExpected.setPlayer1(p1View);
		gExpected.setPlayer2(p2View);
		gExpected.setTurn(1L);

		GameView result = this.gameConverter.convert(g,p1,p2);
		// assertions
		assertFalse(result.isGameOver());
		assertFalse(gExpected.isGameOver());
		Assertions.assertThat(result.getBoard().equals(gExpected.getBoard()));
		Assertions.assertThat(result.isGameOver()).isEqualTo(gExpected.isGameOver());
		
		Assertions.assertThat(result.getPlayer1().equals(gExpected.getPlayer1()));
		Assertions.assertThat(result.getPlayer2().equals(gExpected.getPlayer2()));
		Assertions.assertThat(result.getTurn().equals(gExpected.getTurn()));

	}
}
