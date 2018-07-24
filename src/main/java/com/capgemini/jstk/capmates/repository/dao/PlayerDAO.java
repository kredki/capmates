package com.capgemini.jstk.capmates.repository.dao;

import java.util.List;
import java.util.Optional;

import com.capgemini.jstk.capmates.repository.entities.PlayerEntity;

public interface PlayerDAO {

	List<PlayerEntity> getPlayers();

	Optional<PlayerEntity> getPlayerById(long id);

	PlayerEntity updatePlayer(PlayerEntity player);

	PlayerEntity addPlayer(PlayerEntity playerToAdd);
}