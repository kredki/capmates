package com.capgemini.jstk.capmates.mappers;

import org.springframework.stereotype.Component;

import com.capgemini.jstk.capmates.repository.entities.PlayerEntity;
import com.capgemini.jstk.capmates.services.dto.PlayerDTO;

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
}
