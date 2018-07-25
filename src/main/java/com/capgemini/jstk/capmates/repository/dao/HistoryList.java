package com.capgemini.jstk.capmates.repository.dao;

import java.util.ArrayList;
import java.util.List;
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
	public List<HistoryEntity> getHistoryByGameId(long gameId) {
		return this.historyLists.stream().filter(x -> x.getGameId() == gameId).collect(Collectors.toList());
	}

	@Override
	public HistoryEntity addMatch(HistoryEntity match) {
		this.historyLists.add(match);
		return match;
	}
}
