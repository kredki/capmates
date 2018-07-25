package com.capgemini.jstk.capmates.repository.entities;

import java.time.LocalTime;

public class RemovedHoursEntity {
	private long playerId;
	private LocalTime fromHour;
	private LocalTime toHour;
	private String comment;

	public RemovedHoursEntity(long playerId, LocalTime fromHour, LocalTime toHour, String comment) {
		super();
		this.playerId = playerId;
		this.fromHour = fromHour;
		this.toHour = toHour;
		this.comment = comment;
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
