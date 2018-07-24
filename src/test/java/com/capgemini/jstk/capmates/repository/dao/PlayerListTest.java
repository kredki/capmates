package com.capgemini.jstk.capmates.repository.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jstk.capmates.repository.entities.PlayerEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerListTest {

	@Autowired
	private PlayerDAO playerRepository;

	@Test
	public void shouldReturnPlayersList() {
		// when
		List<PlayerEntity> players = playerRepository.getPlayers();

		// then
		assertEquals(5, players.size());
	}

	@Test
	public void shouldReturnPlayer() {
		// when
		PlayerEntity player = playerRepository.getPlayerById(1).get();

		// then
		assertEquals(1, player.getId());
		assertEquals("Adam", player.getFirstName());
		assertEquals("Nowak", player.getLastName());
		assertEquals("adam@nowak.pl", player.getEmail());
		assertEquals("pass", player.getPassword());
		assertEquals("motto", player.getMotto());
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldThrowException() {
		// when
		playerRepository.getPlayerById(20).get();
	}

	@Test
	public void shouldUpdatePlayer() {
		// given
		PlayerEntity player = new PlayerEntity(5L, "Jarosław", "Psikuta", "jaroslaw@psikuta.pl", "haslo",
				"silna psychika");
		// when
		playerRepository.updatePlayer(player);
		PlayerEntity addedPlayer = playerRepository.getPlayerById(5L).get();

		// then
		assertEquals(5, addedPlayer.getId());
		assertEquals("Jarosław", addedPlayer.getFirstName());
		assertEquals("Psikuta", addedPlayer.getLastName());
		assertEquals("jaroslaw@psikuta.pl", addedPlayer.getEmail());
		assertEquals("haslo", addedPlayer.getPassword());
		assertEquals("silna psychika", addedPlayer.getMotto());
	}

	@Test
	public void shouldAddPlayer() {
		// given
		PlayerEntity player = new PlayerEntity(10L, "Jarosław", "Psikuta", "jaroslaw@psikuta.pl", "haslo",
				"silna psychika");
		// when
		playerRepository.addPlayer(player);
		PlayerEntity addedPlayer = playerRepository.getPlayerById(10L).get();

		// then
		assertEquals(10, addedPlayer.getId());
		assertEquals("Jarosław", addedPlayer.getFirstName());
		assertEquals("Psikuta", addedPlayer.getLastName());
		assertEquals("jaroslaw@psikuta.pl", addedPlayer.getEmail());
		assertEquals("haslo", addedPlayer.getPassword());
		assertEquals("silna psychika", addedPlayer.getMotto());
	}
}