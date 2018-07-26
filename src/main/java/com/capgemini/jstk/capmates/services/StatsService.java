package com.capgemini.jstk.capmates.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.capmates.repository.dao.HistoryDAO;
import com.capgemini.jstk.capmates.repository.entities.HistoryEntity;
import com.capgemini.jstk.capmates.services.dto.HistoryDTO;
import com.capgemini.jstk.capmates.services.dto.StatDTO;

@Service
public class StatsService implements Stats {
	private static final long WIN_POINTS = 10;
	private HistoryDAO historyDAO;

	@Autowired
	public StatsService(HistoryDAO historyDAO) {
		super();
		this.historyDAO = historyDAO;
	}

	@Override
	public StatDTO getStat(long playerId, long gameId) {
		List<HistoryEntity> matchHistory = this.historyDAO.getHistoryForPlayer(playerId, gameId);
		long rank = 0;
		long level = 0;
		long wins = 0;
		long loss = 0;
		for (HistoryEntity match : matchHistory) {
			long points = match.getPoints();
			if (match.getGameId() != gameId) {
				continue;
			}
			if (points == WIN_POINTS) {
				wins++;
			} else {
				loss++;
			}
		}
		level = (wins * WIN_POINTS + loss) / 20 + 1;
		rank = getRank(playerId, gameId);
		return new StatDTO(playerId, gameId, rank, level, wins, loss);
	}

	@Override
	public List<HistoryDTO> getPlayerHistory(long playerId) {
		List<HistoryEntity> playerHistory = this.historyDAO.getHistoryForPlayer(playerId);
		ModelMapper mapper = new ModelMapper();
		java.lang.reflect.Type targetListType = new TypeToken<List<HistoryDTO>>() {
		}.getType();
		return mapper.map(playerHistory, targetListType);
	}

	private long getRank(long playerId, long gameId) {
		Map<Long, Long> unsortMap = this.historyDAO.getPlayersPoints(gameId);
		LinkedHashMap<Long, Long> sortedMap = sortByValue(unsortMap);

		int index = 1;
		for (Long key : sortedMap.keySet()) {
			if (key.equals(new Long(playerId))) {
				return index;
			}
			index++;
		}
		return 0L;
	}

	private static LinkedHashMap<Long, Long> sortByValue(Map<Long, Long> unsortMap) {
		List<Map.Entry<Long, Long>> list = new LinkedList<Map.Entry<Long, Long>>(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<Long, Long>>() {
			public int compare(Map.Entry<Long, Long> o1, Map.Entry<Long, Long> o2) {
				return (o1.getValue()).compareTo(o2.getValue()) * -1; // -1 to sort in desc order
			}
		});

		LinkedHashMap<Long, Long> sortedMap = new LinkedHashMap<Long, Long>();
		for (Map.Entry<Long, Long> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}
}
