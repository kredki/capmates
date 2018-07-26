package com.capgemini.jstk.capmates.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.jstk.capmates.repository.dao.BoardGameDAO;
import com.capgemini.jstk.capmates.repository.dao.PlayerGamesDAO;
import com.capgemini.jstk.capmates.repository.entities.BoardGameEntity;
import com.capgemini.jstk.capmates.services.dto.BoardGameDTO;
import com.capgemini.jstk.capmates.services.dto.GameToAddDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PlayerGamesServiceTest {
	@Mock
	private static BoardGameDAO boardGameDAO;
	@Mock
	private static PlayerGamesDAO playerGamesDAO;

	@InjectMocks
	private PlayerGamesService playerGamesService;

	private static BoardGameEntity game1;
	private static BoardGameEntity game2;

	@Configuration
	static class PlayerGamesServiceTestContextConfiguration {
		@Bean
		public PlayerGamesService playerGamesService() {
			return new PlayerGamesService(boardGameDAO, playerGamesDAO);
		}
	}

	@BeforeClass
	public static void init() {
		game1 = new BoardGameEntity(1L, "title1", 2, 3);
		game2 = new BoardGameEntity(2L, "title2", 4, 6);
	}

	@Test
	public void shouldReturnGames() {
		//given
		List<BoardGameEntity> gamelist = new ArrayList<>();
		gamelist.add(game1);
		gamelist.add(game2);
		Mockito.when(boardGameDAO.getBoardGames()).thenReturn(gamelist);

		//when
		List<BoardGameDTO> games = playerGamesService.getAllGames();

		//then
		assertThat(games.size()).isEqualTo(2);
		BoardGameDTO returnedGame1 = games.get(0);
		BoardGameDTO returnedGame2 = games.get(1);
		assertThat(returnedGame1.getId()).isEqualTo(game1.getId());
		assertThat(returnedGame1.getTitle()).isEqualTo(game1.getTitle());
		assertThat(returnedGame1.getPlayerQtyFrom()).isEqualTo(game1.getPlayerQtyFrom());
		assertThat(returnedGame1.getPlayerQtyTo()).isEqualTo(game1.getPlayerQtyTo());
		assertThat(returnedGame2.getId()).isEqualTo(game2.getId());
		assertThat(returnedGame2.getTitle()).isEqualTo(game2.getTitle());
		assertThat(returnedGame2.getPlayerQtyFrom()).isEqualTo(game2.getPlayerQtyFrom());
		assertThat(returnedGame2.getPlayerQtyTo()).isEqualTo(game2.getPlayerQtyTo());
	}

	@Test
	public void shouldReturnPlayerGames() {
		//given
		List<BoardGameEntity> gamelist = new ArrayList<>();
		gamelist.add(game1);
		gamelist.add(game2);
		Mockito.when(boardGameDAO.getBoardGames()).thenReturn(gamelist);
		Mockito.when(boardGameDAO.getBoardGamesById(Mockito.any())).thenReturn(gamelist);

		//when
		List<BoardGameDTO> games = playerGamesService.getPlayerGames(1L);

		//then
		assertThat(games.size()).isEqualTo(2);
		BoardGameDTO returnedGame1 = games.get(0);
		BoardGameDTO returnedGame2 = games.get(1);
		assertThat(returnedGame1.getId()).isEqualTo(game1.getId());
		assertThat(returnedGame1.getTitle()).isEqualTo(game1.getTitle());
		assertThat(returnedGame1.getPlayerQtyFrom()).isEqualTo(game1.getPlayerQtyFrom());
		assertThat(returnedGame1.getPlayerQtyTo()).isEqualTo(game1.getPlayerQtyTo());
		assertThat(returnedGame2.getId()).isEqualTo(game2.getId());
		assertThat(returnedGame2.getTitle()).isEqualTo(game2.getTitle());
		assertThat(returnedGame2.getPlayerQtyFrom()).isEqualTo(game2.getPlayerQtyFrom());
		assertThat(returnedGame2.getPlayerQtyTo()).isEqualTo(game2.getPlayerQtyTo());
	}

	@Test
	public void shouldAddGame() {
		//given
		String title = game1.getTitle();
		int playerQtyFrom = game1.getPlayerQtyFrom();
		int playerQtyTo = game1.getPlayerQtyTo();
		GameToAddDTO gameToAdd = new GameToAddDTO(title, playerQtyFrom, playerQtyTo);
		long gameId = 10L;
		Mockito.when(boardGameDAO.getBoardGameByTitle(title)).thenReturn(Optional.ofNullable(null));
		BoardGameEntity addedGame = new BoardGameEntity(gameId, title, playerQtyFrom, playerQtyTo);
		Mockito.when(boardGameDAO.addBoardGame(Mockito.notNull())).thenReturn(Optional.ofNullable(addedGame));

		//when
		Optional<BoardGameDTO> game = playerGamesService.addGame(1L, gameToAdd);

		//then
		assertTrue(game.isPresent());
		BoardGameDTO returnedGame1 = game.get();
		assertThat(returnedGame1.getId()).isEqualTo(gameId);
		assertThat(returnedGame1.getTitle()).isEqualTo(title);
		assertThat(returnedGame1.getPlayerQtyFrom()).isEqualTo(playerQtyFrom);
		assertThat(returnedGame1.getPlayerQtyTo()).isEqualTo(playerQtyTo);
	}

	@Test
	public void shouldAddAlreadyExistingGame() {
		//given
		long gameId = 10L;
		String title = game1.getTitle();
		int playerQtyFrom = game1.getPlayerQtyFrom();
		int playerQtyTo = game1.getPlayerQtyTo();
		BoardGameDTO gameToAdd = new BoardGameDTO(gameId, title, playerQtyFrom, playerQtyTo);
		;
		Mockito.when(boardGameDAO.getBoardGameByTitle(title)).thenReturn(Optional.ofNullable(null));
		BoardGameEntity addedGame = new BoardGameEntity(gameId, title, playerQtyFrom, playerQtyTo);
		Mockito.when(boardGameDAO.addBoardGame(Mockito.notNull())).thenReturn(Optional.ofNullable(addedGame));

		//when
		Optional<BoardGameDTO> game = playerGamesService.addGame(1L, gameToAdd);

		//then
		assertTrue(game.isPresent());
		BoardGameDTO returnedGame1 = game.get();
		assertThat(returnedGame1.getId()).isEqualTo(gameId);
		assertThat(returnedGame1.getTitle()).isEqualTo(title);
		assertThat(returnedGame1.getPlayerQtyFrom()).isEqualTo(playerQtyFrom);
		assertThat(returnedGame1.getPlayerQtyTo()).isEqualTo(playerQtyTo);
	}
}
