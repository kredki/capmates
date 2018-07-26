package com.capgemini.jstk.capmates.services;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.capgemini.jstk.capmates.services.dto.AvailabilityHoursDTO;
import com.capgemini.jstk.capmates.services.dto.ChallengeDTO;

public interface AvailabilityHours {

	Optional<AvailabilityHoursDTO> addAvailabilityHours(AvailabilityHoursDTO hoursToAdd);

	Optional<AvailabilityHoursDTO> removeAvailabilityHours(AvailabilityHoursDTO hoursToRemove, String comment);

	List<ChallengeDTO> matchAvailability(long playerId, LocalTime minOverlapingTime);

}