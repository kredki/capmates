package com.capgemini.jstk.capmates.services.dto;

import java.time.LocalTime;

public class ChallengeDTO {
	private long challengerId;
	private long opponentId;
	private LocalTime opponentFromHour;
	private LocalTime opponentToHour;

	public ChallengeDTO(long challengerId, long opponentId, LocalTime opponentFromHour, LocalTime opponentToHour) {
		super();
		this.challengerId = challengerId;
		this.opponentId = opponentId;
		this.opponentFromHour = opponentFromHour;
		this.opponentToHour = opponentToHour;
	}

	public long getChallengerId() {
		return challengerId;
	}

	public void setChallengerId(long challengerId) {
		this.challengerId = challengerId;
	}

	public long getOpponentId() {
		return opponentId;
	}

	public void setOpponentId(long opponentId) {
		this.opponentId = opponentId;
	}

	public LocalTime getOpponentFromHour() {
		return opponentFromHour;
	}

	public void setOpponentFromHour(LocalTime opponentFromHour) {
		this.opponentFromHour = opponentFromHour;
	}

	public LocalTime getOpponentToHour() {
		return opponentToHour;
	}

	public void setOpponentToHour(LocalTime opponentToHour) {
		this.opponentToHour = opponentToHour;
	}
}
