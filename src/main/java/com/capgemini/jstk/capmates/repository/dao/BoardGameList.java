package com.capgemini.jstk.capmates.repository.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
		boardGameList = new ArrayList<>();
	}

	@PostConstruct
	private void init() {
		boardGameList.add(new BoardGameEntity(counter.getAndIncrement(), "Agricola", 1, 5));
		boardGameList.add(new BoardGameEntity(counter.getAndIncrement(), "Hansa Teutonica", 2, 5));
		boardGameList.add(new BoardGameEntity(counter.getAndIncrement(), "Pax Pamir", 2, 5));
		boardGameList.add(new BoardGameEntity(counter.getAndIncrement(), "Troyes", 2, 4));
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
	public BoardGameEntity updateBoardGame(BoardGameEntity boardGame) {
		long id = boardGame.getId();
		for (BoardGameEntity bg : boardGameList) {
			if (bg.getId() == id) {
				bg.setTitle(boardGame.getTitle());
				bg.setPlayerQtyFrom(boardGame.getPlayerQtyFrom());
				bg.setPlayerQtyTo(boardGame.getPlayerQtyTo());
				return bg;
			}
		}
		return null;
	}

	@Override
	public BoardGameEntity addBoardGame(BoardGameEntity gameToAdd) {
		String title = gameToAdd.getTitle();
		for (BoardGameEntity bg : boardGameList) {
			if (bg.getTitle().equals(title)) {
				return null;
			}
		}
		gameToAdd.setId(this.counter.getAndIncrement());
		this.boardGameList.add(gameToAdd);
		return gameToAdd;
	}
}
