package com.capgemini.jstk.capmates.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.capmates.mappers.PlayerMapper;
import com.capgemini.jstk.capmates.repository.dao.PlayerDAO;
import com.capgemini.jstk.capmates.repository.entities.PlayerEntity;
import com.capgemini.jstk.capmates.services.dto.PlayerDTO;
import com.capgemini.jstk.capmates.services.dto.PlayerToAddDTO;

@Service
public class PlayerService implements Player {
	PlayerMapper playerMapper;
	PlayerDAO playerDAO;

	@Autowired
	public PlayerService(PlayerMapper playerMapper, PlayerDAO playerDAO) {
		super();
		this.playerMapper = playerMapper;
		this.playerDAO = playerDAO;
	}

	@Override
	public Optional<PlayerDTO> updatePlayer(PlayerDTO playerToUpdate) {
		PlayerEntity playerEntity = this.playerMapper.mapToEntity(playerToUpdate);
		if (this.playerDAO.updatePlayer(playerEntity) != null) {
			PlayerEntity updatedPlayer = this.playerDAO.getPlayerById(playerEntity.getId()).get();
			return Optional.ofNullable(this.playerMapper.mapToDTO(updatedPlayer));
		} else {
			return Optional.ofNullable(null);
		}
	}

	@Override
	public Optional<PlayerDTO> addPlayer(PlayerToAddDTO playerToAddDTO) {
		PlayerEntity playerEntity = this.playerMapper.mapToEntity(0L, playerToAddDTO);
		if (this.playerDAO.addPlayer(playerEntity) != null) {
			PlayerEntity addedPlayer = this.playerDAO.getPlayerById(playerEntity.getId()).get();
			return Optional.ofNullable(this.playerMapper.mapToDTO(addedPlayer));
		} else {
			return Optional.ofNullable(null);
		}
	}

	@Override
	public List<PlayerDTO> getPlayers() {
		List<PlayerEntity> players = this.playerDAO.getPlayers();
		return players.stream().map(x -> playerMapper.mapToDTO(x)).collect(Collectors.toList());
	}

	@Override
	public Optional<PlayerDTO> getPlayer(long id) {
		Optional<PlayerEntity> player = this.playerDAO.getPlayerById(id);
		if (player.isPresent()) {
			return Optional.ofNullable(playerMapper.mapToDTO(player.get()));
		}
		return Optional.ofNullable(null);
	}
}
