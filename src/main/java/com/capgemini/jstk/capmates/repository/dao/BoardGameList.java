package com.capgemini.jstk.capmates.repository.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.capgemini.jstk.capmates.repository.entities.BoardGameEntity;

@Repository
@Scope("singleton")
public class BoardGameList implements BoardGameDAO {
	private final List<BoardGameEntity> boardGameList;
	private static final AtomicLong counter = new AtomicLong(1);

	public BoardGameList() {
		boardGameList = new CopyOnWriteArrayList<>();
	}

	@PostConstruct
	private void init() {
		boardGameList.add(new BoardGameEntity(counter.getAndIncrement(), "Agricola"));
		boardGameList.add(new BoardGameEntity(counter.getAndIncrement(), "Hansa Teutonica"));
		boardGameList.add(new BoardGameEntity(counter.getAndIncrement(), "Pax Pamir"));
		boardGameList.add(new BoardGameEntity(counter.getAndIncrement(), "Troyes"));
	}

	@Override
	public List<BoardGameEntity> getBoardGames() {
		return boardGameList;
	}

	@Override
	public Optional<BoardGameEntity> getBoardGameById(long id) {
		for (BoardGameEntity boardGame : boardGameList) {
			if (boardGame.getId() == id) {
				return Optional.ofNullable(boardGame);
			}
		}
		return Optional.ofNullable(null);
	}

	@Override
	public Optional<BoardGameEntity> getBoardGameByTitle(String title) {
		for (BoardGameEntity boardGame : boardGameList) {
			if (boardGame.getTitle().equals(title)) {
				return Optional.ofNullable(boardGame);
			}
		}
		return Optional.ofNullable(null);
	}

	@Override
	public boolean setBoardGame(BoardGameEntity boardGame) {
		long id = boardGame.getId();
		for (BoardGameEntity bg : boardGameList) {
			if (bg.getId() == id) {
				bg.setTitle(boardGame.getTitle());
				return true;
			}
		}
		return false;
	}
}
