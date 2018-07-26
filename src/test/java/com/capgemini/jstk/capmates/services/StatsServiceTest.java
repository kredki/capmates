package com.capgemini.jstk.capmates.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.jstk.capmates.repository.dao.HistoryDAO;
import com.capgemini.jstk.capmates.repository.entities.HistoryEntity;
import com.capgemini.jstk.capmates.services.dto.HistoryDTO;
import com.capgemini.jstk.capmates.services.dto.StatDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class StatsServiceTest {
	@Mock
	private static HistoryDAO historyDAO;

	@InjectMocks
	private StatsService statsService;

	@Configuration
	static class StatsServiceTestContextConfiguration {
		@Bean
		public StatsService statsService() {
			return new StatsService(historyDAO);
		}
	}

	@Test
	public void shouldReturnStats() {
		//given
		long playerId = 1L;
		long gameId = 1L;
		List<HistoryEntity> history = new ArrayList<>();
		history.add(new HistoryEntity(playerId, 1L, 1L));
		history.add(new HistoryEntity(playerId, 1L, 1L));
		history.add(new HistoryEntity(playerId, 1L, 10L));
		history.add(new HistoryEntity(playerId, 2L, 10L));
		Mockito.when(historyDAO.getHistoryForPlayer(1L, 1L)).thenReturn(history);
		Map<Long, Long> points = new LinkedHashMap<>();
		points.put(2L, 23L);
		points.put(playerId, 22L);
		points.put(5L, 111L);
		points.put(4L, 1L);
		Mockito.when(historyDAO.getPlayersPoints(gameId)).thenReturn(points);

		//when
		StatDTO stats = statsService.getStat(playerId, gameId);

		//then
		assertThat(stats.getPlayerId()).isEqualTo(playerId);
		assertThat(stats.getLevel()).isEqualTo(1L);
		assertThat(stats.getLoss()).isEqualTo(2L);
		assertThat(stats.getWins()).isEqualTo(1L);
		assertThat(stats.getRank()).isEqualTo(3L);
	}

	@Test
	public void shouldReturnHistory() {
		//given
		long playerId = 1L;
		long gameId = 1L;
		List<HistoryEntity> history = new ArrayList<>();
		history.add(new HistoryEntity(playerId, 1L, 1L));
		history.add(new HistoryEntity(playerId, 1L, 1L));
		history.add(new HistoryEntity(playerId, 1L, 10L));
		history.add(new HistoryEntity(playerId, 2L, 10L));
		Mockito.when(historyDAO.getHistoryForPlayer(playerId)).thenReturn(history);

		//when
		List<HistoryDTO> playerHistory = statsService.getPlayerHistory(playerId);

		//then
		assertThat(playerHistory.size()).isEqualTo(4);
		for (HistoryDTO historyDTO : playerHistory) {
			assertThat(historyDTO.getPlayerId()).isEqualTo(playerId);
		}
	}
}
