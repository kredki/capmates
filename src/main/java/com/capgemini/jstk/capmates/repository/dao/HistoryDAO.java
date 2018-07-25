package com.capgemini.jstk.capmates.repository.dao;

import java.util.List;

import com.capgemini.jstk.capmates.repository.entities.HistoryEntity;

public interface HistoryDAO {

	List<HistoryEntity> getHistoryForGame(long gameId);

	HistoryEntity addMatch(HistoryEntity match);

	List<HistoryEntity> getHistoryForPlayer(Long playerId, long gameId);

}