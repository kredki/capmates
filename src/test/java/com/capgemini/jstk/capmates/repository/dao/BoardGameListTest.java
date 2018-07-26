package com.capgemini.jstk.capmates.repository.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.jstk.capmates.repository.entities.BoardGameEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BoardGameListTest {
	@Autowired
	private BoardGameList boardGameRepository;

	@Configuration
	static class BoardGameListTestContextConfiguration {
		@Bean
		public BoardGameList boardGameList() {
			return new BoardGameList();
		}
	}

	@Test
	public void shouldReturnGamesList() {
		// when
		List<BoardGameEntity> games = boardGameRepository.getBoardGames();

		// then
		assertThat(games.size()).isGreaterThanOrEqualTo(4);
	}

	@Test
	public void shouldReturnGameById() {
		// when
		BoardGameEntity game = this.boardGameRepository.getBoardGameById(1).get();

		// then
		assertEquals(1, game.getId());
		assertEquals("Agricola", game.getTitle());
		assertEquals(1, game.getPlayerQtyFrom());
		assertEquals(5, game.getPlayerQtyTo());
	}

	@Test
	public void shouldReturnGameByTitle() {
		// when
		BoardGameEntity game = this.boardGameRepository.getBoardGameByTitle("Agricola").get();

		// then
		assertEquals(1, game.getId());
		assertEquals("Agricola", game.getTitle());
		assertEquals(1, game.getPlayerQtyFrom());
		assertEquals(5, game.getPlayerQtyTo());
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldThrowException() {
		// when
		this.boardGameRepository.getBoardGameById(20).get();
	}

	@Test
	public void shouldUpdateGame() {
		// given
		BoardGameEntity gameToUpdate = new BoardGameEntity(3, "Pax Renaissance", 5, 6);

		// when
		BoardGameEntity game = this.boardGameRepository.updateBoardGame(gameToUpdate);

		// then
		assertEquals(3, game.getId());
		assertEquals("Pax Renaissance", game.getTitle());
		assertEquals(5, game.getPlayerQtyFrom());
		assertEquals(6, game.getPlayerQtyTo());
	}

	@Test
	public void shouldAddGame() {
		// given
		BoardGameEntity gameToUpdate = new BoardGameEntity(3, "Pax Renaissance2", 2, 4);

		// when
		BoardGameEntity game = this.boardGameRepository.addBoardGame(gameToUpdate).get();

		// then
		assertEquals(5, game.getId());
		assertEquals("Pax Renaissance2", game.getTitle());
		assertEquals(2, game.getPlayerQtyFrom());
		assertEquals(4, game.getPlayerQtyTo());
	}
}
