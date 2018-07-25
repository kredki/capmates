package com.capgemini.jstk.capmates.mappers;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.capgemini.jstk.capmates.repository.entities.AvailabilityHoursEntity;
import com.capgemini.jstk.capmates.services.dto.AvailabilityHoursDTO;

@Component
public class AvailabilityHoursMapper {
	public AvailabilityHoursEntity mapToEntity(AvailabilityHoursDTO hoursToMap) {
		long id = hoursToMap.getPlayerId();
		LocalTime from = hoursToMap.getFromHour();
		LocalTime to = hoursToMap.getToHour();
		return new AvailabilityHoursEntity(id, from, to);
	}

	public AvailabilityHoursDTO mapToDTO(AvailabilityHoursEntity hoursToMap) {
		long id = hoursToMap.getPlayerId();
		LocalTime from = hoursToMap.getFromHour();
		LocalTime to = hoursToMap.getToHour();
		return new AvailabilityHoursDTO(id, from, to);
	}
}
