package com.capgemini.jstk.capmates.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.jstk.capmates.services.PlayerGames;
import com.capgemini.jstk.capmates.services.dto.BoardGameDTO;

@Controller
@ResponseBody
@RequestMapping(value = "/games")
public class BoardGameController {
	@Autowired
	private PlayerGames playerGamesService;

	@GetMapping(value = "/")
	public ResponseEntity<List<BoardGameDTO>> getAllGames() {
		return ResponseEntity.ok(playerGamesService.getAllGames());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BoardGameDTO> getGame(@PathVariable("id") long gameId) {
		Optional<BoardGameDTO> game = playerGamesService.getGame(gameId);
		if (game.isPresent()) {
			return ResponseEntity.ok(game.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
