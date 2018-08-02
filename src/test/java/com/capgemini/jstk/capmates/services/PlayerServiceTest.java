package com.capgemini.jstk.capmates.services;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.jstk.capmates.mappers.PlayerMapper;
import com.capgemini.jstk.capmates.repository.dao.interfaces.PlayerDAO;
import com.capgemini.jstk.capmates.repository.entities.PlayerEntity;
import com.capgemini.jstk.capmates.services.dto.PlayerDTO;
import com.capgemini.jstk.capmates.services.dto.PlayerToAddDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PlayerServiceTest {
	@Mock
	private static PlayerMapper playerMapperMock;

	@Mock
	private static PlayerDAO playerDAOMock;

	@InjectMocks
	private PlayerService playerService;

	private static PlayerEntity playerEntity;
	private static PlayerDTO playerDTO;
	private static PlayerToAddDTO playerToAddDTO;
	private static PlayerDTO nonExistingPlayerDTO;
	private static PlayerEntity nonExistingplPlayerEntity;

	@Configuration
	static class PlayerServiceTestContextConfiguration {
		@Bean
		public PlayerService playerService() {
			return new PlayerService(playerMapperMock, playerDAOMock);
		}
	}

	@BeforeClass
	public static void init() {
		playerEntity = new PlayerEntity(1L, "Jan", "Nowak", "jan@nowak.pl", "pass", "motto");
		playerDTO = new PlayerDTO(1L, "Jan", "Nowak", "jan@nowak.pl", "pass", "motto");
		playerToAddDTO = new PlayerToAddDTO("Jan", "Nowak", "jan@nowak.pl", "pass", "motto");
		nonExistingPlayerDTO = new PlayerDTO(2L, "Jan", "Nowak", "jan@nowak.pl", "pass", "motto");
		nonExistingplPlayerEntity = new PlayerEntity(2L, "Jan", "Nowak", "jan@nowak.pl", "pass", "motto");
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(this.playerMapperMock.mapToDTO(playerEntity)).thenReturn(playerDTO);
		Mockito.when(this.playerDAOMock.updatePlayer(playerEntity)).thenReturn(playerEntity);
		Mockito.when(this.playerDAOMock.updatePlayer(nonExistingplPlayerEntity)).thenReturn(null);
		Mockito.when(this.playerDAOMock.addPlayer(playerEntity)).thenReturn(playerEntity);
		Mockito.when(this.playerDAOMock.getPlayerById(1L)).thenReturn(Optional.ofNullable(playerEntity));
		Mockito.when(this.playerDAOMock.getPlayers()).thenReturn(java.util.Arrays.asList(playerEntity));

		Mockito.when(this.playerMapperMock.mapToDTO(playerEntity)).thenReturn(playerDTO);
		Mockito.when(this.playerMapperMock.mapToEntity(playerDTO)).thenReturn(playerEntity);
		Mockito.when(this.playerMapperMock.mapToEntity(0L, playerToAddDTO)).thenReturn(playerEntity);
	}

	@Test
	public void shouldAddPlayer() {
		// when
		PlayerDTO player = this.playerService.addPlayer(this.playerToAddDTO).get();

		// then
		assertEquals(playerToAddDTO.getFirstName(), player.getFirstName());
		assertEquals(playerToAddDTO.getLastName(), player.getLastName());
		assertEquals(playerToAddDTO.getMotto(), player.getMotto());
		assertEquals(playerToAddDTO.getPassword(), player.getPassword());
	}

	@Test
	public void shouldUpdatePlayer() {
		// when
		PlayerDTO player = this.playerService.updatePlayer(this.playerDTO).get();

		// then
		assertEquals(playerDTO.getId(), player.getId());
		assertEquals(playerDTO.getFirstName(), player.getFirstName());
		assertEquals(playerDTO.getLastName(), player.getLastName());
		assertEquals(playerDTO.getMotto(), player.getMotto());
		assertEquals(playerDTO.getPassword(), player.getPassword());
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldNotUpdatePlayer() {
		// then
		this.playerService.updatePlayer(nonExistingPlayerDTO).get();
	}

	@Test
	public void shouldReturnPlayers() {
		// when
		List<PlayerDTO> players = this.playerService.getPlayers();

		// then
		assertEquals(1, players.size());
		assertEquals(playerDTO, players.get(0));
	}
}
