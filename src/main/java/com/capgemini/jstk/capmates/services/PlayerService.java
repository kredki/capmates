package com.capgemini.jstk.capmates.services;

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

	public boolean updatePlayer(PlayerDTO playerToUpdate) {
		PlayerEntity playerEntity = this.playerMapper.mapToEntity(playerToUpdate);
		if (this.playerDAO.updatePlayer(playerEntity)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addPlayer(PlayerToAddDTO playerToAddDTO) {
		long id = this.playerDAO.getNextIdAndIncrement();
		PlayerEntity playerEntity = this.playerMapper.mapToEntity(id, playerToAddDTO);
		if (this.playerDAO.addPlayer(playerEntity)) {
			return true;
		} else {
			return false;
		}
	}
}
