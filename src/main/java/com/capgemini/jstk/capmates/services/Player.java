package com.capgemini.jstk.capmates.services;

import java.util.List;
import java.util.Optional;

import com.capgemini.jstk.capmates.services.dto.PlayerDTO;
import com.capgemini.jstk.capmates.services.dto.PlayerToAddDTO;

public interface Player {

	Optional<PlayerDTO> updatePlayer(PlayerDTO playerToUpdate);

	Optional<PlayerDTO> addPlayer(PlayerToAddDTO playerToAddDTO);

	List<PlayerDTO> getPlayers();

}