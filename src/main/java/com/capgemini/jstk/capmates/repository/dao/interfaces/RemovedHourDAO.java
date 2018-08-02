package com.capgemini.jstk.capmates.repository.dao.interfaces;

import java.util.List;
import java.util.Optional;

import com.capgemini.jstk.capmates.repository.entities.RemovedHoursEntity;

public interface RemovedHourDAO {

	List<RemovedHoursEntity> getRemovedHoursById(long playerId);

	Optional<RemovedHoursEntity> addRemovedHour(RemovedHoursEntity hourToAdd);

}