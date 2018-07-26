package com.capgemini.jstk.capmates.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
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
}