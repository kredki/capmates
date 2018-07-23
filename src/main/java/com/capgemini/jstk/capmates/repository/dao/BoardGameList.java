package com.capgemini.jstk.capmates.repository.dao;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.capgemini.jstk.capmates.repository.entities.BoardGame;

@Repository
@Scope("singleton")
public class BoardGameList {
	private final CopyOnWriteArrayList<BoardGame> boardGameList;
	private static final AtomicLong counter = new AtomicLong(1);

	public BoardGameList() {
		boardGameList = new CopyOnWriteArrayList<>();
	}

	@PostConstruct
	private void init() {
		boardGameList.add(new BoardGame(counter.getAndIncrement(), "Agricola"));
		boardGameList.add(new BoardGame(counter.getAndIncrement(), "Hansa Teutonica"));
		boardGameList.add(new BoardGame(counter.getAndIncrement(), "Pax Pamir"));
		boardGameList.add(new BoardGame(counter.getAndIncrement(), "Troyes"));
	}

}
