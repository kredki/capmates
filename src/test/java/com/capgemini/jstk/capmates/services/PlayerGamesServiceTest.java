package com.capgemini.jstk.capmates.services;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.jstk.capmates.repository.dao.BoardGameDAO;
import com.capgemini.jstk.capmates.repository.dao.PlayerGamesDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PlayerGamesServiceTest {
	@Mock
	private static BoardGameDAO boardGameDAO;
	@Mock
	private static PlayerGamesDAO playerGamesDAO;

	@InjectMocks
	private PlayerGamesService playerGamesService;

	@Configuration
	static class PlayerGamesServiceTestContextConfiguration {
		@Bean
		public PlayerGamesService playerGamesService() {
			return new PlayerGamesService(boardGameDAO, playerGamesDAO);
		}
	}

	@BeforeClass
	public static void init() {
		// todo
	}

	@Before
	public void setUp() {
		//todo
	}

	@Test
	public void should() {
		//todo
	}
}
