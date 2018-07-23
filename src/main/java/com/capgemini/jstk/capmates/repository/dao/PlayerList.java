package com.capgemini.jstk.capmates.repository.dao;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.capgemini.jstk.capmates.repository.entities.Player;

@Repository
@Scope("singleton")
public class PlayerList {
	private final CopyOnWriteArrayList<Player> playerList;
	private static final AtomicLong counter = new AtomicLong(1);

	@Autowired
	public PlayerList() {
		playerList = new CopyOnWriteArrayList<>();
	}

	@PostConstruct
	private void init() {
		playerList.add(new Player(counter.getAndIncrement(), "Adam", "Nowak", "adam@nowak.pl", "pass", "motto"));
		playerList.add(new Player(counter.getAndIncrement(), "Jan", "Kowalski", "mail@mail.com", "pass",
				"kokodżambo i do przodu"));
		playerList.add(new Player(counter.getAndIncrement(), "John", "Doe", "abc@xyz.it", "pass2", "lubie placki"));
		playerList.add(new Player(counter.getAndIncrement(), "John", "Rambo", "john@rambo.com", "haslo", "rambo"));
		playerList.add(new Player(counter.getAndIncrement(), "Arnold", "Schwartzenegger", "arni@terminator.pl", "pass",
				"I'll be back"));
	}
}
