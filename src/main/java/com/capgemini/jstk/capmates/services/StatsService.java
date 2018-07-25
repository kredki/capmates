package com.capgemini.jstk.capmates.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.capmates.repository.dao.HistoryDAO;
import com.capgemini.jstk.capmates.repository.entities.HistoryEntity;
import com.capgemini.jstk.capmates.services.dto.StatDTO;

@Service
public class StatsService {
	private static final long WIN_POINTS = 10;
	private HistoryDAO historyDAO;

	@Autowired
	public StatsService(HistoryDAO historyDAO) {
		super();
		this.historyDAO = historyDAO;
	}

	public StatDTO getStat(long playerId, long gameId) {
		List<HistoryEntity> matchHistory = this.historyDAO.getHistoryForPlayer(playerId, gameId);
		long rank = 0;
		long level = 0;
		long wins = 0;
		long loss = 0;
		for (HistoryEntity match : matchHistory) {
			long points = match.getPoints();
			if (points == WIN_POINTS) {
				wins++;
			} else {
				loss++;
			}
		}
		// todo
		return null;
	}
}
