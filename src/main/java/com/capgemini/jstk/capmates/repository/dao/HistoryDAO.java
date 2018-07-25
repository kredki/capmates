package com.capgemini.jstk.capmates.repository.dao;

import java.util.List;

import com.capgemini.jstk.capmates.repository.entities.HistoryEntity;

public interface HistoryDAO {

	List<HistoryEntity> getHistoryByGameId(long gameId);

	HistoryEntity addMatch(HistoryEntity match);

}