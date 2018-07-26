package com.capgemini.jstk.capmates.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.jstk.capmates.mappers.AvailabilityHoursMapper;
import com.capgemini.jstk.capmates.repository.dao.AvailabilityHoursDAO;
import com.capgemini.jstk.capmates.repository.dao.RemovedHourDAO;
import com.capgemini.jstk.capmates.repository.entities.AvailabilityHoursEntity;
import com.capgemini.jstk.capmates.repository.entities.RemovedHoursEntity;
import com.capgemini.jstk.capmates.services.dto.AvailabilityHoursDTO;
import com.capgemini.jstk.capmates.services.dto.ChallengeDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AvailabilityHoursServiceTest {
	@Mock
	private static AvailabilityHoursDAO availabilityHoursDAOMock;
	@Mock
	private static AvailabilityHoursMapper availabilityHoursMapperMock;
	@Mock
	private static RemovedHourDAO removedHourDAOMock;

	@InjectMocks
	private AvailabilityHoursService availabilityHoursService;

	private static AvailabilityHoursEntity availabilityHoursEntity;
	private static AvailabilityHoursDTO availabilityHoursDTO;
	private static RemovedHoursEntity removedHoursEntity;

	@Configuration
	static class AvailabilityHoursServiceTestContextConfiguration {
		@Bean
		public AvailabilityHoursService availabilityHoursService() {
			return new AvailabilityHoursService(availabilityHoursDAOMock, availabilityHoursMapperMock,
					removedHourDAOMock);
		}
	}

	@BeforeClass
	public static void init() {
		long playerId = 1L;
		LocalTime fromHour = LocalTime.parse("10:00");
		LocalTime toHour = LocalTime.parse("12:00");
		availabilityHoursEntity = new AvailabilityHoursEntity(playerId, fromHour, toHour);
		availabilityHoursDTO = new AvailabilityHoursDTO(playerId, fromHour, toHour);
		removedHoursEntity = new RemovedHoursEntity(playerId, fromHour, toHour, "comment");
	}

	@Before
	public void setUp() {
		Mockito.when(availabilityHoursMapperMock.mapToDTO(availabilityHoursEntity)).thenReturn(availabilityHoursDTO);
		Mockito.when(availabilityHoursMapperMock.mapToEntity(availabilityHoursDTO)).thenReturn(availabilityHoursEntity);
		Mockito.when(availabilityHoursDAOMock.addAvailabilityHours(availabilityHoursEntity))
				.thenReturn(Optional.ofNullable(availabilityHoursEntity));
		Mockito.when(availabilityHoursDAOMock.removeAvailabilityHours(availabilityHoursEntity))
				.thenReturn(Optional.ofNullable(availabilityHoursEntity));
	}

	@Test
	public void shouldAddHours() {
		//when
		Optional<AvailabilityHoursDTO> hours = availabilityHoursService.addAvailabilityHours(availabilityHoursDTO);

		//then
		assertTrue(hours.isPresent());
		AvailabilityHoursDTO hoursDTO = hours.get();
		assertThat(hoursDTO.getPlayerId()).isEqualTo(availabilityHoursDTO.getPlayerId());
		assertThat(hoursDTO.getFromHour()).isEqualTo(availabilityHoursDTO.getFromHour());
		assertThat(hoursDTO.getToHour()).isEqualTo(availabilityHoursDTO.getToHour());
	}

	@Test
	public void shouldNotAddHours() {
		//given
		Mockito.when(availabilityHoursDAOMock.addAvailabilityHours(availabilityHoursEntity))
				.thenReturn(Optional.ofNullable(null));

		//when
		Optional<AvailabilityHoursDTO> hours = availabilityHoursService.addAvailabilityHours(availabilityHoursDTO);

		//then
		assertFalse(hours.isPresent());
	}

	@Test
	public void shouldRemoveHours() {
		//given
		Mockito.when(removedHourDAOMock.addRemovedHour(Mockito.any()))
				.thenReturn(Optional.ofNullable(removedHoursEntity));

		//when
		Optional<AvailabilityHoursDTO> hours = availabilityHoursService.removeAvailabilityHours(availabilityHoursDTO,
				"comment");

		//then
		assertTrue(hours.isPresent());
		AvailabilityHoursDTO hoursDTO = hours.get();
		assertThat(hoursDTO.getPlayerId()).isEqualTo(availabilityHoursDTO.getPlayerId());
		assertThat(hoursDTO.getFromHour()).isEqualTo(availabilityHoursDTO.getFromHour());
		assertThat(hoursDTO.getToHour()).isEqualTo(availabilityHoursDTO.getToHour());
	}

	@Test
	public void shouldreturnedMatchedHours() {
		//given
		long player1Id = 1L;
		long player2Id = 2L;
		long player3Id = 3L;
		long player4Id = 4L;
		List<AvailabilityHoursEntity> player1List = new ArrayList<>();
		player1List.add(new AvailabilityHoursEntity(player1Id, LocalTime.parse("10:00"), LocalTime.parse("12:00")));
		player1List.add(new AvailabilityHoursEntity(player1Id, LocalTime.parse("08:00"), LocalTime.parse("08:20")));
		Mockito.when(availabilityHoursDAOMock.getAvailabilityHoursByPlayerId(1L)).thenReturn(player1List);

		List<AvailabilityHoursEntity> otherPlayersList = new ArrayList<>();
		otherPlayersList
				.add(new AvailabilityHoursEntity(player2Id, LocalTime.parse("10:00"), LocalTime.parse("11:00")));

		otherPlayersList
				.add(new AvailabilityHoursEntity(player2Id, LocalTime.parse("08:00"), LocalTime.parse("08:20")));

		otherPlayersList
				.add(new AvailabilityHoursEntity(player3Id, LocalTime.parse("07:00"), LocalTime.parse("07:30")));

		otherPlayersList
				.add(new AvailabilityHoursEntity(player3Id, LocalTime.parse("10:00"), LocalTime.parse("10:30")));

		otherPlayersList
				.add(new AvailabilityHoursEntity(player4Id, LocalTime.parse("20:00"), LocalTime.parse("22:00")));

		Mockito.when(availabilityHoursDAOMock.getAvailabilityHoursOfOtherPlayers(player1Id))
				.thenReturn(otherPlayersList);

		//when
		List<ChallengeDTO> challenges = availabilityHoursService.matchAvailability(player1Id, LocalTime.parse("00:30"));

		//then
		assertThat(challenges.size()).isEqualTo(2);
		ChallengeDTO challenge1 = challenges.get(0);
		ChallengeDTO challenge2 = challenges.get(1);
		assertThat(challenge1.getOpponentId()).isEqualTo(2L);
		assertThat(challenge2.getOpponentId()).isEqualTo(3L);
	}
}