package com.capgemini.jstk.capmates.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.jstk.capmates.services.Player;
import com.capgemini.jstk.capmates.services.PlayerGames;
import com.capgemini.jstk.capmates.services.dto.BoardGameDTO;
import com.capgemini.jstk.capmates.services.dto.GameToAddDTO;

@Controller
@ResponseBody
@RequestMapping
public class BoardGameController {
	@Autowired
	private PlayerGames playerGamesService;
	@Autowired
	private Player playerService;

	@GetMapping(value = "/games")
	public ResponseEntity<List<BoardGameDTO>> getAllGames(
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "playerQtyFrom", defaultValue = "0") int playerQtyFrom,
			@RequestParam(value = "playerQtyTo", defaultValue = "2147483647") int playerQtyTo) {
		return ResponseEntity.ok(playerGamesService.getAllGames(title, playerQtyFrom, playerQtyTo));
	}

	@GetMapping(value = "/games/{id}")
	public ResponseEntity<BoardGameDTO> getGame(@PathVariable("id") long gameId) {
		Optional<BoardGameDTO> game = playerGamesService.getGame(gameId);
		if (game.isPresent()) {
			return ResponseEntity.ok(game.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping(value = "/player/{id}/games")
	public ResponseEntity<List<BoardGameDTO>> getPlayerGames(@PathVariable("id") long playerId) {
		if (!playerService.getPlayer(playerId).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(playerGamesService.getPlayerGames(playerId));
	}

	@PostMapping(value = "/player/{id}/games")
	public ResponseEntity<BoardGameDTO> addPlayerGame(@PathVariable("id") long playerId,
			@RequestBody GameToAddDTO gameToAdd, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		if (!playerService.getPlayer(playerId).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		Optional<BoardGameDTO> addedGame = playerGamesService.addGame(playerId, gameToAdd);
		if (addedGame.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(addedGame.get());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}
