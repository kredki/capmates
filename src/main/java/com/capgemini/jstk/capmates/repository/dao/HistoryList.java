package com.capgemini.jstk.capmates.repository.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.capgemini.jstk.capmates.repository.entities.HistoryEntity;

@Repository
public class HistoryList implements HistoryDAO {
	private List<HistoryEntity> historyLists;

	public HistoryList() {
		super();
		this.historyLists = new ArrayList<>();
	}

	@PostConstruct
	private void init() {
		this.historyLists.add(new HistoryEntity(1L, 1L, 1L));
		this.historyLists.add(new HistoryEntity(1L, 1L, 1L));
		this.historyLists.add(new HistoryEntity(1L, 1L, 10L));
		this.historyLists.add(new HistoryEntity(2L, 1L, 10L));
		this.historyLists.add(new HistoryEntity(2L, 1L, 10L));
		this.historyLists.add(new HistoryEntity(2L, 1L, 1L));
		this.historyLists.add(new HistoryEntity(3L, 2L, 10L));
		this.historyLists.add(new HistoryEntity(3L, 3L, 10L));
		this.historyLists.add(new HistoryEntity(3L, 4L, 10L));
		this.historyLists.add(new HistoryEntity(4L, 2L, 10L));
		this.historyLists.add(new HistoryEntity(4L, 2L, 1L));
		this.historyLists.add(new HistoryEntity(4L, 2L, 1L));
		this.historyLists.add(new HistoryEntity(5L, 4L, 1L));
		this.historyLists.add(new HistoryEntity(5L, 4L, 1L));
		this.historyLists.add(new HistoryEntity(5L, 4L, 1L));
		this.historyLists.add(new HistoryEntity(5L, 4L, 1L));
	}

	@Override
	public List<HistoryEntity> getHistoryForGame(long gameId) {
		return this.historyLists.stream().filter(x -> x.getGameId() == gameId).collect(Collectors.toList());
	}

	@Override
	public List<HistoryEntity> getHistoryForPlayer(Long playerId, long gameId) {
		return this.historyLists.stream().filter(x -> x.getPlayerId() == playerId && x.getGameId() == gameId)
				.collect(Collectors.toList());
	}

	@Override
	public HistoryEntity addMatch(HistoryEntity match) {
		this.historyLists.add(match);
		return match;
	}

	@Override
	public Map<Long, Long> getPlayersPoints(long gameId) {
		List<HistoryEntity> historyForGame = getHistoryForGame(gameId);
		Map<Long, Long> points = new LinkedHashMap<>();
		for (HistoryEntity history : historyForGame) {
			long playerId = history.getPlayerId();
			Long value = points.get(playerId);
			long playerPoints = history.getPoints();
			if (value != null) {
				value += playerPoints;
			} else {
				value = new Long(playerPoints);
			}
			points.put(playerId, playerPoints);
		}
		return points;
	}

	@Override
	public List<HistoryEntity> getHistoryForPlayer(Long playerId) {
		return this.historyLists.stream().filter(x -> x.getPlayerId() == playerId).collect(Collectors.toList());
	}
}
