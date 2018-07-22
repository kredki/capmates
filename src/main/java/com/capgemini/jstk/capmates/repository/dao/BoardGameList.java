package com.capgemini.jstk.capmates.repository.dao;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.capgemini.jstk.capmates.repository.entities.BoardGame;

@Repository
public class BoardGameList {
	private final CopyOnWriteArrayList<BoardGame> boardGameList;

	public BoardGameList() {
		boardGameList = new CopyOnWriteArrayList<>();
	}

	@PostConstruct
	private void init() {
		boardGameList.add(new BoardGame("Agricola"));
		boardGameList.add(new BoardGame("Hansa Teutonica"));
		boardGameList.add(new BoardGame("Pax Pamir"));
		boardGameList.add(new BoardGame("Troyes"));
	}

}
