package com.capgemini.jstk.capmates.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.capgemini.jstk.capmates.CapmatesApplication;
import com.capgemini.jstk.capmates.services.Player;
import com.capgemini.jstk.capmates.services.PlayerGames;
import com.capgemini.jstk.capmates.services.dto.BoardGameDTO;
import com.capgemini.jstk.capmates.services.dto.GameToAddDTO;
import com.capgemini.jstk.capmates.services.dto.PlayerDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CapmatesApplication.class)
@WebAppConfiguration
public class BoardGameControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Mock
	private PlayerGames playerGamesService;

	@Mock
	private Player playerService;

	@Autowired
	private BoardGameController boardGameController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(playerGamesService);
		Mockito.reset(playerGamesService);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		ReflectionTestUtils.setField(boardGameController, "playerGamesService", playerGamesService); // wstrzykniecie zmockowanego serwisu do pola kontrolera
		ReflectionTestUtils.setField(boardGameController, "playerService", playerService);
	}

	@Test
	public void shouldReturnGames() throws Exception {
		// given
		final List<BoardGameDTO> expectedList = new ArrayList<>();
		expectedList.add(new BoardGameDTO(1L, "title1", 2, 4));
		expectedList.add(new BoardGameDTO(2L, "title2", 3, 5));

		Mockito.when(playerGamesService.getAllGames("", 0, Integer.MAX_VALUE)).thenReturn(expectedList);

		// when
		ResultActions resultActions = mockMvc.perform(get("/games"));

		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value("1"))
				.andExpect(jsonPath("$[1].id").value("2"));
	}

	@Test
	public void shouldReturnGamesWhenParametersInURL() throws Exception {
		// given
		final List<BoardGameDTO> expectedList = new ArrayList<>();
		expectedList.add(new BoardGameDTO(1L, "title1", 2, 4));
		expectedList.add(new BoardGameDTO(2L, "title2", 3, 5));

		Mockito.when(playerGamesService.getAllGames("title", 2, 5)).thenReturn(expectedList);

		// when
		ResultActions resultActions = mockMvc.perform(get("/games?title=title&playerQtyFrom=2&playerQtyTo=5"));

		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value("1"))
				.andExpect(jsonPath("$[1].id").value("2"));
	}

	@Test
	public void shouldReturnGame() throws Exception {
		// given
		final BoardGameDTO expected = new BoardGameDTO(1L, "title1", 2, 4);

		Mockito.when(playerGamesService.getGame(1L)).thenReturn(Optional.ofNullable(expected));

		// when
		ResultActions resultActions = mockMvc.perform(get("/games/1"));

		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.title").value("title1")).andExpect(jsonPath("$.playerQtyFrom").value("2"))
				.andExpect(jsonPath("$.playerQtyTo").value("4"));
	}

	@Test
	public void shouldNotReturnGame() throws Exception {
		// given
		Mockito.when(playerGamesService.getGame(1L)).thenReturn(Optional.ofNullable(null));

		// when
		ResultActions resultActions = mockMvc.perform(get("/games/1"));

		// then
		resultActions.andExpect(status().isNotFound());
	}

	@Test
	public void shouldReturnPlayerGames() throws Exception {
		// given
		final List<BoardGameDTO> expectedList = new ArrayList<>();
		expectedList.add(new BoardGameDTO(1L, "title1", 2, 4));
		expectedList.add(new BoardGameDTO(2L, "title2", 3, 5));

		Mockito.when(playerGamesService.getPlayerGames(1L)).thenReturn(expectedList);
		Mockito.when(playerService.getPlayer(1L)).thenReturn(Optional.ofNullable(new PlayerDTO()));

		// when
		ResultActions resultActions = mockMvc.perform(get("/player/1/games"));

		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value("1"))
				.andExpect(jsonPath("$[1].id").value("2"));
	}

	@Test
	public void shouldNotReturnPlayerGames() throws Exception {
		// given
		final List<BoardGameDTO> expectedList = new ArrayList<>();
		expectedList.add(new BoardGameDTO(1L, "title1", 2, 4));
		expectedList.add(new BoardGameDTO(2L, "title2", 3, 5));
		long playerId = 1L;

		Mockito.when(playerGamesService.getPlayerGames(playerId)).thenReturn(expectedList);
		Mockito.when(playerService.getPlayer(playerId)).thenReturn(Optional.ofNullable(null));

		// when
		ResultActions resultActions = mockMvc.perform(get("/player/1/games"));

		// then
		resultActions.andExpect(status().isNotFound());
	}

	@Test
	public void shouldAddPlayerGame() throws Exception {
		// given
		String title = "title";
		int playerQtyFrom = 1;
		int playerQtyTo = 2;
		final GameToAddDTO gameToAdd = new GameToAddDTO(title, playerQtyFrom, playerQtyTo);
		long gameId = 1L;
		final BoardGameDTO returnedGame = new BoardGameDTO(gameId, title, playerQtyFrom, playerQtyTo);
		long playerId = 1L;
		String gameToAddJson = "{\"title\":\"title\",\"playerQtyFrom\":1, \"playerQtyTo\":2}";

		Mockito.when(playerGamesService.addGame(Mockito.anyLong(), Mockito.any(GameToAddDTO.class)))
				.thenReturn(Optional.ofNullable(returnedGame));
		Mockito.when(playerService.getPlayer(playerId)).thenReturn(Optional.ofNullable(new PlayerDTO()));
		ObjectMapper mapper = new ObjectMapper();
		try {

			gameToAddJson = mapper.writeValueAsString(gameToAdd);
			System.out.println("jsonString = " + gameToAddJson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// when
		ResultActions resultActions = mockMvc
				.perform(post("/player/1/games").content(gameToAddJson).contentType(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.title").value("title")).andExpect(jsonPath("$.playerQtyFrom").value("1"))
				.andExpect(jsonPath("$.playerQtyTo").value("2"));
	}
}
