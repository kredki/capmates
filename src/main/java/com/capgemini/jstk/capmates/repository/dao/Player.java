package com.capgemini.jstk.capmates.repository.dao;

import java.util.List;
import java.util.Optional;

import com.capgemini.jstk.capmates.repository.entities.PlayerEntity;

public interface Player {

	List<PlayerEntity> getPlayers();

	Optional<PlayerEntity> getPlayerById(long id);

	boolean setPlayer(PlayerEntity player);

}