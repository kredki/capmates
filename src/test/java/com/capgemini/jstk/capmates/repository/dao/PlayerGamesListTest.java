package com.capgemini.jstk.capmates.repository.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.jstk.capmates.repository.entities.PlayerBoardGameEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PlayerGamesListTest {
	@Autowired
	private PlayerGamesList playerGamesRepository;

	@Configuration
	static class PlayerGamesListTestContextConfiguration {
		@Bean
		public PlayerGamesList playerGamesListTest() {
			return new PlayerGamesList();
		}
	}

	@Before
	public void setup() {
		playerGamesRepository.reset();
	}

	@Test
	public void shouldReturnList() {
		//when
		List<Long> games = playerGamesRepository.getPlayerGames(1L);

		//then
		assertThat(games.size()).isEqualTo(3);
	}

	@Test
	public void shouldAddGame() {
		//given
		PlayerBoardGameEntity gameToAdd = new PlayerBoardGameEntity(5L, 1L);
		int sizeBeforeAddition = playerGamesRepository.getPlayerGames(5L).size();

		//when
		PlayerBoardGameEntity game = playerGamesRepository.addBoardGame(gameToAdd);

		//then
		assertThat(playerGamesRepository.getPlayerGames(1L).size()).isEqualTo(sizeBeforeAddition + 1);
		assertThat(game.getBoardGameId()).isEqualTo(1L);
		assertThat(game.getPlayerId()).isEqualTo(5L);
	}

	@Test
	public void shouldNotAddGame() {
		//given
		PlayerBoardGameEntity gameToAdd = new PlayerBoardGameEntity(3L, 3L);
		int sizeBeforeAddition = playerGamesRepository.getPlayerGames(3L).size();

		//when
		PlayerBoardGameEntity game = playerGamesRepository.addBoardGame(gameToAdd);

		//then
		assertThat(playerGamesRepository.getPlayerGames(1L).size()).isEqualTo(sizeBeforeAddition);
		assertThat(game.getBoardGameId()).isEqualTo(3L);
		assertThat(game.getPlayerId()).isEqualTo(3L);
	}
}
