package com.capgemini.jstk.capmates.services;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.capmates.mappers.AvailabilityHoursMapper;
import com.capgemini.jstk.capmates.repository.dao.AvailabilityHoursDAO;
import com.capgemini.jstk.capmates.repository.dao.RemovedHourDAO;
import com.capgemini.jstk.capmates.repository.entities.AvailabilityHoursEntity;
import com.capgemini.jstk.capmates.repository.entities.RemovedHoursEntity;
import com.capgemini.jstk.capmates.services.dto.AvailabilityHoursDTO;
import com.capgemini.jstk.capmates.services.dto.ChallengeDTO;

@Service
public class AvailabilityHoursService {
	private static final int HALF_OF_MAX_MATCHED_HOURS = 20;
	private static final int MAX_MATCHED_HOURS = 40;
	private AvailabilityHoursDAO availabilityHoursDAO;
	private AvailabilityHoursMapper availabilityHoursMapper;
	private RemovedHourDAO removedHourDAO;

	@Autowired
	public AvailabilityHoursService(AvailabilityHoursDAO availabilityHoursDAO,
			AvailabilityHoursMapper availabilityHoursMapper, RemovedHourDAO removedHourDAO) {
		super();
		this.availabilityHoursDAO = availabilityHoursDAO;
		this.availabilityHoursMapper = availabilityHoursMapper;
		this.removedHourDAO = removedHourDAO;
	}

	public Optional<AvailabilityHoursDTO> addAvailabilityHours(AvailabilityHoursDTO hoursToAdd) {
		Optional<AvailabilityHoursEntity> addedHours = this.availabilityHoursDAO
				.addAvailabilityHours(availabilityHoursMapper.mapToEntity(hoursToAdd));
		if (addedHours.isPresent()) {
			return Optional.ofNullable(this.availabilityHoursMapper.mapToDTO(addedHours.get()));
		} else {
			return Optional.ofNullable(null);
		}
	}

	public Optional<AvailabilityHoursDTO> removeAvailabilityHours(AvailabilityHoursDTO hoursToRemove, String comment) {
		Optional<AvailabilityHoursEntity> removedHours = this.availabilityHoursDAO
				.removeAvailabilityHours(availabilityHoursMapper.mapToEntity(hoursToRemove));
		if (removedHours.isPresent()) {
			AvailabilityHoursDTO removedHoursDTO = this.availabilityHoursMapper.mapToDTO(removedHours.get());
			ModelMapper mapper = new ModelMapper();
			Optional<RemovedHoursEntity> result = removedHourDAO
					.addRemovedHour(mapper.map(removedHoursDTO, RemovedHoursEntity.class));
			return Optional.ofNullable(mapper.map(result, AvailabilityHoursDTO.class));
		} else {
			return Optional.ofNullable(null);
		}
	}

	public List<ChallengeDTO> matchAvailability(long playerId, LocalTime minOverlapingTime) {
		List<AvailabilityHoursEntity> playerHours = this.availabilityHoursDAO.getAvailabilityHoursByPlayerId(playerId);
		List<AvailabilityHoursEntity> otherPlayersHours = this.availabilityHoursDAO
				.getAvailabilityHoursOfOtherPlayers(playerId);
		List<AvailabilityHoursEntity> matchedHours = new ArrayList<>();
		int addedHours = 0;
		for (AvailabilityHoursEntity hours : playerHours) {
			matchedHours.addAll(getMatchedHours(hours, otherPlayersHours, minOverlapingTime));
			addedHours = matchedHours.size();
			if (addedHours >= MAX_MATCHED_HOURS) {
				return ChangeToChallenge(playerId, matchedHours);
			}
		}
		return ChangeToChallenge(playerId, matchedHours);
	}

	private List<AvailabilityHoursEntity> getMatchedHours(AvailabilityHoursEntity playerHours,
			List<AvailabilityHoursEntity> otherPlayersHours, LocalTime minOverlapingTime) {
		List<AvailabilityHoursEntity> matchedHours = new ArrayList<>();
		int addedHours = 0;
		for (AvailabilityHoursEntity hour : otherPlayersHours) {
			if (isTimeOvelaping(playerHours, hour, minOverlapingTime)) {
				matchedHours.add(hour);
				addedHours++;
			}
			if (addedHours >= HALF_OF_MAX_MATCHED_HOURS) {
				return matchedHours;
			}
		}
		return matchedHours;
	}

	private boolean isTimeOvelaping(AvailabilityHoursEntity time1, AvailabilityHoursEntity time2,
			LocalTime minOverlapingTime) {
		LocalTime from;
		LocalTime to;
		LocalTime time1From = time1.getFromHour();
		LocalTime time2From = time2.getFromHour();
		LocalTime time1To = time1.getToHour();
		LocalTime time2To = time2.getToHour();
		if (time1From.isBefore(time2From)) {
			from = time2From;
		} else {
			from = time1From;
		}
		if (time1To.isBefore(time2To)) {
			to = time1To;
		} else {
			to = time2To;
		}
		long minMinutes = ChronoUnit.MINUTES.between(LocalTime.parse("00:00"), minOverlapingTime);
		long minutesBetween = ChronoUnit.MINUTES.between(from, to);
		if (minMinutes <= minutesBetween) {
			return true;
		} else {
			return false;
		}
	}

	private List<ChallengeDTO> ChangeToChallenge(Long challengerId, List<AvailabilityHoursEntity> matchedHours) {
		List<ChallengeDTO> result = new ArrayList<>();
		long opponentId;
		LocalTime opponentFromHour;
		LocalTime opponentToHour;
		for (AvailabilityHoursEntity hour : matchedHours) {
			opponentId = hour.getPlayerId();
			opponentFromHour = hour.getFromHour();
			opponentToHour = hour.getToHour();
			result.add(new ChallengeDTO(challengerId, opponentId, opponentFromHour, opponentToHour));
		}
		return result;
	}
}