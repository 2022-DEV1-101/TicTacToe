package com.game.tictactoe.converterView;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.game.tictactoe.entity.Player;
import com.game.tictactoe.view.PlayerView;

@DataJpaTest
public class PlayerConverterTest {

	private PlayerConverter playerConverter = new PlayerConverter();

	@Test
	@DisplayName("convert Player to PlayerView test")
	public void playerToView(){
		Player player = new Player();
		player.setId(1L);
		player.setUserName("test");
		player.setSymbole("x");
		PlayerView playerView =this.playerConverter.convert(player);
		PlayerView expectedPlayerView = new PlayerView();
		expectedPlayerView.setId(1L);
		expectedPlayerView.setSymbole("x");
		expectedPlayerView.setUserName("test");
		Assertions.assertThat(playerView.getId()).isEqualTo(expectedPlayerView.getId());
		Assertions.assertThat(playerView.getSymbole()).isEqualTo(expectedPlayerView.getSymbole());
		Assertions.assertThat(playerView.getUserName()).isEqualTo(expectedPlayerView.getUserName());
		
	}
}
