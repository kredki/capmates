package com.capgemini.jstk.capmates.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.capmates.mappers.PlayerMapper;
import com.capgemini.jstk.capmates.repository.dao.PlayerDAO;
import com.capgemini.jstk.capmates.repository.entities.PlayerEntity;
import com.capgemini.jstk.capmates.services.dto.PlayerDTO;
import com.capgemini.jstk.capmates.services.dto.PlayerToAddDTO;

@Service
public class PlayerService {
	PlayerMapper playerMapper;
	PlayerDAO playerDAO;

	@Autowired
	public PlayerService(PlayerMapper playerMapper, PlayerDAO playerDAO) {
		super();
		this.playerMapper = playerMapper;
		this.playerDAO = playerDAO;
	}

	public Optional<PlayerEntity> updatePlayer(PlayerDTO playerToUpdate) {
		PlayerEntity playerEntity = this.playerMapper.mapToEntity(playerToUpdate);
		if (this.playerDAO.updatePlayer(playerEntity)) {
			PlayerEntity updatedPlayer = this.playerDAO.getPlayerById(playerEntity.getId()).get();
			return Optional.ofNullable(updatedPlayer);
		} else {
			return Optional.ofNullable(null);
		}
	}

	public Optional<PlayerEntity> addPlayer(PlayerToAddDTO playerToAddDTO) {
		long id = this.playerDAO.getNextIdAndIncrement();
		PlayerEntity playerEntity = this.playerMapper.mapToEntity(id, playerToAddDTO);
		if (this.playerDAO.addPlayer(playerEntity)) {
			PlayerEntity addedPlayer = this.playerDAO.getPlayerById(playerEntity.getId()).get();
			return Optional.ofNullable(addedPlayer);
		} else {
			return Optional.ofNullable(null);
		}
	}
}
