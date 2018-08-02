package com.capgemini.jstk.capmates.repository.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.capgemini.jstk.capmates.exceptions.NoSuchIndexException;
import com.capgemini.jstk.capmates.repository.entities.PlayerEntity;

@Repository
@Scope("singleton")
public class PlayerList implements PlayerDAO {
	private final List<PlayerEntity> playerList;
	private static final AtomicLong counter = new AtomicLong(1);

	@Autowired
	public PlayerList() {
		playerList = new ArrayList<>();
	}

	@PostConstruct
	private void init() {
		playerList.add(new PlayerEntity(counter.getAndIncrement(), "Adam", "Nowak", "adam@nowak.pl", "pass", "motto"));
		playerList.add(new PlayerEntity(counter.getAndIncrement(), "Jan", "Kowalski", "mail@mail.com", "pass",
				"kokodżambo i do przodu"));
		playerList
				.add(new PlayerEntity(counter.getAndIncrement(), "John", "Doe", "abc@xyz.it", "pass2", "lubie placki"));
		playerList
				.add(new PlayerEntity(counter.getAndIncrement(), "John", "Rambo", "john@rambo.com", "haslo", "rambo"));
		playerList.add(new PlayerEntity(counter.getAndIncrement(), "Arnold", "Schwartzenegger", "arni@terminator.pl",
				"pass", "I'll be back"));
	}

	@Override
	public List<PlayerEntity> getPlayers() {
		return this.playerList;
	}

	@Override
	public Optional<PlayerEntity> getPlayerById(long id) {
		for (PlayerEntity player : playerList) {
			if (player.getId() == id) {
				return Optional.ofNullable(player);
			}
		}
		throw new NoSuchIndexException("No player of index " + id);
	}

	@Override
	public PlayerEntity updatePlayer(PlayerEntity player) {
		long playerId = player.getId();
		for (PlayerEntity p : playerList) {
			if (p.getId() == playerId) {
				p.setEmail(player.getEmail());
				p.setFirstName(player.getFirstName());
				p.setLastName(player.getLastName());
				p.setMotto(player.getMotto());
				p.setPassword(player.getPassword());
				return p;
			}
		}
		return null;
	}

	public PlayerEntity addPlayer(PlayerEntity playerToAdd) {
		long id = playerToAdd.getId();
		for (PlayerEntity player : playerList) {
			if (player.getId() == id) {
				return player;
			}
		}
		playerToAdd.setId(this.counter.getAndIncrement());
		this.playerList.add(playerToAdd);
		return playerToAdd;
	}

	public void reset() {
		counter.set(1L);
		this.playerList.clear();
		init();
	}
}
