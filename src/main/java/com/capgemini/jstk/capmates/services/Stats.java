package com.capgemini.jstk.capmates.services;

import java.util.List;

import com.capgemini.jstk.capmates.services.dto.HistoryDTO;
import com.capgemini.jstk.capmates.services.dto.StatDTO;

public interface Stats {

	StatDTO getStat(long playerId, long gameId);

	List<HistoryDTO> getPlayerHistory(long playerId);

}