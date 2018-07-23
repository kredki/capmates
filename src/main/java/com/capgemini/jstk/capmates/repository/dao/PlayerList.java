package com.capgemini.jstk.capmates.repository.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.capgemini.jstk.capmates.repository.entities.PlayerEntity;

@Repository
@Scope("singleton")
public class PlayerList implements Player {
	private final List<PlayerEntity> playerList;
	private static final AtomicLong counter = new AtomicLong(1);

	@Autowired
	public PlayerList() {
		playerList = new CopyOnWriteArrayList<>();
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
		return Optional.ofNullable(null);
	}

	@Override
	public boolean setPlayer(PlayerEntity player) {
		long playerId = player.getId();
		for (PlayerEntity p : playerList) {
			if (p.getId() == playerId) {
				p.setEmail(player.getEmail());
				p.setFirstName(player.getFirstName());
				p.setLastName(player.getLastName());
				p.setMotto(player.getMotto());
				p.setPassword(player.getPassword());
				return true;
			}
		}
		return false;
	}
}
