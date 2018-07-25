package com.capgemini.jstk.capmates.repository.entities;

import java.time.LocalTime;

public class AvailabilityHoursEntity {
	private long playerId;
	private LocalTime fromHour;
	private LocalTime toHour;

	public AvailabilityHoursEntity(long playerId, LocalTime fromHour, LocalTime toHour) {
		super();
		this.playerId = playerId;
		this.fromHour = fromHour;
		this.toHour = toHour;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public LocalTime getFromHour() {
		return fromHour;
	}

	public void setFromHour(LocalTime fromHour) {
		this.fromHour = fromHour;
	}

	public LocalTime getToHour() {
		return toHour;
	}

	public void setToHour(LocalTime toHour) {
		this.toHour = toHour;
	}
}
