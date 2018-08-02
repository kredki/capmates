package com.capgemini.jstk.capmates.repository.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.capgemini.jstk.capmates.repository.dao.interfaces.RemovedHourDAO;
import com.capgemini.jstk.capmates.repository.entities.RemovedHoursEntity;

@Repository
public class RemovedHoursList implements RemovedHourDAO {
	private List<RemovedHoursEntity> removedHoursList;

	public RemovedHoursList() {
		super();
		this.removedHoursList = new ArrayList<>();
	}

	@PostConstruct
	private void init() {
		LocalTime fromHour = LocalTime.parse("10:00");
		LocalTime toHour = LocalTime.parse("13:00");
		this.removedHoursList.add(new RemovedHoursEntity(1L, fromHour, toHour, "comment1"));
		this.removedHoursList.add(new RemovedHoursEntity(1L, fromHour, toHour, "comment2"));
		this.removedHoursList.add(new RemovedHoursEntity(2L, fromHour, toHour, "comment3"));
	}

	@Override
	public List<RemovedHoursEntity> getRemovedHoursById(long playerId) {
		return this.removedHoursList.stream().filter(x -> x.getPlayerId() == playerId).collect(Collectors.toList());
	}

	@Override
	public Optional<RemovedHoursEntity> addRemovedHour(RemovedHoursEntity hourToAdd) {
		this.removedHoursList.add(hourToAdd);
		return Optional.ofNullable(hourToAdd);
	}

	public void reset() {
		this.removedHoursList.clear();
		init();
	}
}
