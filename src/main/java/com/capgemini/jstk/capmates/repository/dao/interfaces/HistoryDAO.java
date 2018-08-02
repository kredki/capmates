package com.capgemini.jstk.capmates.repository.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.capgemini.jstk.capmates.repository.entities.HistoryEntity;

public interface HistoryDAO {

	List<HistoryEntity> getHistoryForGame(long gameId);

	HistoryEntity addMatch(HistoryEntity match);

	List<HistoryEntity> getHistoryForPlayer(Long playerId, long gameId);

	List<HistoryEntity> getHistoryForPlayer(Long playerId);

	Map<Long, Long> getPlayersPoints(long gameId);
}