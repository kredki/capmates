package com.capgemini.jstk.capmates.mappers;

import org.springframework.stereotype.Component;

import com.capgemini.jstk.capmates.repository.entities.PlayerEntity;
import com.capgemini.jstk.capmates.services.dto.PlayerDTO;
import com.capgemini.jstk.capmates.services.dto.PlayerToAddDTO;

@Component
public class PlayerMapper {
	public PlayerEntity mapToEntity(PlayerDTO playerDTO) {
		long id = playerDTO.getId();
		String firstName = playerDTO.getFirstName();
		String lastName = playerDTO.getLastName();
		String email = playerDTO.getEmail();
		String password = playerDTO.getPassword();
		String motto = playerDTO.getMotto();
		return new PlayerEntity(id, firstName, lastName, email, password, motto);
	}

	public PlayerEntity mapToEntity(long id, PlayerToAddDTO playerToAddDTO) {
		String firstName = playerToAddDTO.getFirstName();
		String lastName = playerToAddDTO.getLastName();
		String email = playerToAddDTO.getEmail();
		String password = playerToAddDTO.getPassword();
		String motto = playerToAddDTO.getMotto();
		return new PlayerEntity(id, firstName, lastName, email, password, motto);
	}

	public PlayerDTO mapToDTO(PlayerEntity playerEntity) {
		long id = playerEntity.getId();
		String firstName = playerEntity.getFirstName();
		String lastName = playerEntity.getLastName();
		String email = playerEntity.getEmail();
		String password = playerEntity.getPassword();
		String motto = playerEntity.getMotto();
		return new PlayerDTO(id, firstName, lastName, email, password, motto);
	}
}
