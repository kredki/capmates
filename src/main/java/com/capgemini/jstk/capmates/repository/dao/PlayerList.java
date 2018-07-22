package com.capgemini.jstk.capmates.repository.dao;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.capgemini.jstk.capmates.repository.entities.Player;

@Component
@Scope("singleton")
public class PlayerList {
	private final CopyOnWriteArrayList<Player> playerList;

	@Autowired
	public PlayerList() {
		playerList = new CopyOnWriteArrayList<>();
	}

	@PostConstruct
	private void init() {
		playerList.add(new Player("Adam", "Nowak", "adam@nowak.pl", "pass", "motto"));
		playerList.add(
				new Player("Jan", "Kowalski", "mail@mail.com", "pass", "kokodżambo i do przodu"));
		playerList.add(new Player("John", "Doe", "abc@xyz.it", "pass2", "lubie placki"));
		playerList.add(new Player("John", "Rambo", "john@rambo.com", "haslo", "rambo"));
		playerList.add(new Player("Arnold", "Schwartzenegger", "arni@terminator.pl", "pass",
				"I'll be back"));
	}
}
