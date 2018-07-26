package com.capgemini.jstk.capmates.services.dto;

import java.time.LocalTime;

public class AvailabilityHoursDTO {
	private long playerId;
	private LocalTime fromHour;
	private LocalTime toHour;
	private String comment;

	public AvailabilityHoursDTO() {
		super();
	}

	public AvailabilityHoursDTO(long playerId, LocalTime fromHour, LocalTime toHour) {
		super();
		this.playerId = playerId;
		this.fromHour = fromHour;
		this.toHour = toHour;
		this.comment = "";
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}