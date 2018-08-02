package com.capgemini.jstk.capmates.repository.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.capgemini.jstk.capmates.repository.dao.interfaces.AvailabilityHoursDAO;
import com.capgemini.jstk.capmates.repository.entities.AvailabilityHoursEntity;

@Repository
public class AvailabilityHoursList implements AvailabilityHoursDAO {
	private List<AvailabilityHoursEntity> availabilityHoursList;

	public AvailabilityHoursList() {
		super();
		this.availabilityHoursList = new ArrayList<>();
	}

	@PostConstruct
	private void init() {
		LocalTime fromHour = LocalTime.parse("10:00");
		LocalTime toHour = LocalTime.parse("13:00");
		LocalTime fromHour2 = LocalTime.parse("18:00");
		LocalTime fromHour3 = LocalTime.parse("20:00");
		LocalTime toHour2 = LocalTime.parse("19:00");
		LocalTime toHour3 = LocalTime.parse("22:00");
		LocalTime fromHour4 = LocalTime.parse("08:00");
		LocalTime toHour4 = LocalTime.parse("09:30");
		this.availabilityHoursList.add(new AvailabilityHoursEntity(1L, fromHour, toHour));
		this.availabilityHoursList.add(new AvailabilityHoursEntity(2L, fromHour, toHour));
		this.availabilityHoursList.add(new AvailabilityHoursEntity(3L, fromHour, toHour2));
		this.availabilityHoursList.add(new AvailabilityHoursEntity(4L, fromHour2, toHour3));
		this.availabilityHoursList.add(new AvailabilityHoursEntity(1L, fromHour2, toHour2));
		this.availabilityHoursList.add(new AvailabilityHoursEntity(2L, fromHour3, toHour3));
		this.availabilityHoursList.add(new AvailabilityHoursEntity(5L, fromHour4, toHour4));
	}

	@Override
	public List<AvailabilityHoursEntity> getAvailabilityHours() {
		return this.availabilityHoursList;
	}

	@Override
	public List<AvailabilityHoursEntity> getAvailabilityHoursByPlayerId(long id) {
		List<AvailabilityHoursEntity> result = this.availabilityHoursList.stream().filter(x -> x.getPlayerId() == id)
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public List<AvailabilityHoursEntity> getAvailabilityHoursOfOtherPlayers(long ignoredId) {
		List<AvailabilityHoursEntity> result = this.availabilityHoursList.stream()
				.filter(x -> x.getPlayerId() != ignoredId).collect(Collectors.toList());
		return result;
	}

	@Override
	public Optional<AvailabilityHoursEntity> addAvailabilityHours(AvailabilityHoursEntity hoursToAdd) {
		long id = hoursToAdd.getPlayerId();
		LocalTime from = hoursToAdd.getFromHour();
		LocalTime to = hoursToAdd.getToHour();
		for (AvailabilityHoursEntity ah : this.availabilityHoursList) {
			if (ah.getPlayerId() == id && ah.getFromHour().equals(from) && ah.getToHour().equals(to)) {
				return Optional.ofNullable(null);
			}
		}
		this.availabilityHoursList.add(hoursToAdd);
		return Optional.ofNullable(hoursToAdd);
	}

	@Override
	public Optional<AvailabilityHoursEntity> removeAvailabilityHours(AvailabilityHoursEntity hoursToRemove) {
		long id = hoursToRemove.getPlayerId();
		LocalTime from = hoursToRemove.getFromHour();
		LocalTime to = hoursToRemove.getToHour();
		Iterator<AvailabilityHoursEntity> iterator = this.availabilityHoursList.iterator();
		while (iterator.hasNext()) {
			AvailabilityHoursEntity ah = iterator.next();
			if (ah.getPlayerId() == id && ah.getFromHour().equals(from) && ah.getToHour().equals(to)) {
				iterator.remove();
				return Optional.ofNullable(ah);
			}
		}
		return Optional.ofNullable(null);
	}

	public void reset() {
		this.availabilityHoursList.clear();
		init();
	}
}