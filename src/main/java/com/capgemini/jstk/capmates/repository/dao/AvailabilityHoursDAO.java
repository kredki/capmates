package com.capgemini.jstk.capmates.repository.dao;

import java.util.List;
import java.util.Optional;

import com.capgemini.jstk.capmates.repository.entities.AvailabilityHoursEntity;

public interface AvailabilityHoursDAO {

	List<AvailabilityHoursEntity> getAvailabilityHours();

	List<AvailabilityHoursEntity> getAvailabilityHoursByPlayerId(long id);

	List<AvailabilityHoursEntity> getAvailabilityHoursOfOtherPlayers(long ignoredId);

	Optional<AvailabilityHoursEntity> addAvailabilityHours(AvailabilityHoursEntity hoursToAdd);

	Optional<AvailabilityHoursEntity> removeAvailabilityHours(AvailabilityHoursEntity hoursToRemove);

}