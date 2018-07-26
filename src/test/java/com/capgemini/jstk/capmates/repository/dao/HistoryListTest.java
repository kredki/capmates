package com.capgemini.jstk.capmates.repository.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.jstk.capmates.repository.entities.HistoryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class HistoryListTest {
	@Autowired
	private HistoryList historyRepository;

	@Configuration
	static class HistoryListTestContextConfiguration {
		@Bean
		public HistoryList historyList() {
			return new HistoryList();
		}
	}

	@Test
	public void shouldReturnHistory() {
		//when
		List<HistoryEntity> games = historyRepository.getHistoryForGame(1L);

		//then
		assertThat(games.size()).isGreaterThanOrEqualTo(6);
		for (HistoryEntity history : games) {
			assertThat(history.getGameId()).isEqualTo(1L);
		}
	}

	@Test
	public void shouldReturnHistoryForPlayer() {
		//when
		List<HistoryEntity> games = historyRepository.getHistoryForPlayer(3L);

		//then
		assertThat(games.size()).isEqualTo(3);
		for (HistoryEntity history : games) {
			assertThat(history.getPlayerId()).isEqualTo(3L);
		}
	}

	@Test
	public void shouldReturnHistoryForPlayerOfGeme() {
		//when
		List<HistoryEntity> games = historyRepository.getHistoryForPlayer(3L, 3L);

		//then
		assertThat(games.size()).isEqualTo(1);
		for (HistoryEntity history : games) {
			assertThat(history.getPlayerId()).isEqualTo(3L);
		}
	}

	@Test
	public void shouldReturnEmptyHistory() {
		//when
		List<HistoryEntity> games = historyRepository.getHistoryForPlayer(3L, 1L);

		//then
		assertThat(games.size()).isEqualTo(0);
	}

	@Test
	public void shouldAddHistory() {
		//given
		HistoryEntity match = historyRepository.addMatch(new HistoryEntity(5L, 4L, 1L));

		//when
		HistoryEntity history = historyRepository.addMatch(match);

		//then
		assertThat(match.getPlayerId()).isEqualTo(5L);
		assertThat(match.getGameId()).isEqualTo(4L);
		assertThat(match.getPoints()).isEqualTo(1L);
	}

	@Test
	public void shouldReturnPoints() {
		//when
		Map<Long, Long> points = historyRepository.getPlayersPoints(1L);

		//then
		assertThat(points.size()).isEqualTo(2);
		assertThat(points.get(1L)).isEqualTo(12L);
		assertThat(points.get(2L)).isEqualTo(21L);
	}
}