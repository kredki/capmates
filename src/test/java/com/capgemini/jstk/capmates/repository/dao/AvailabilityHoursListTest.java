package com.capgemini.jstk.capmates.repository.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.jstk.capmates.repository.entities.AvailabilityHoursEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AvailabilityHoursListTest {
	@Autowired
	private AvailabilityHoursList availabilityHoursRepository;

	@Configuration
	static class AvailabilityHoursListTestContextConfiguration {
		@Bean
		public AvailabilityHoursList availabilityHoursList() {
			return new AvailabilityHoursList();
		}
	}

	@Test
	public void shouldReturnAvailabilityHours() {
		//when
		List<AvailabilityHoursEntity> hours = availabilityHoursRepository.getAvailabilityHours();
		//then
		assertThat(hours.size()).isGreaterThanOrEqualTo(7);
	}

	@Test
	public void shouldReturnPlayerAvailabilityHours() {
		//when
		List<AvailabilityHoursEntity> hours = availabilityHoursRepository.getAvailabilityHoursByPlayerId(1L);

		//then
		assertThat(hours.size()).isEqualTo(2);
		for (int i = 0; i < hours.size(); i++) {
			assertThat(hours.get(i).getPlayerId()).isEqualTo(1L);
		}
	}

	@Test
	public void shouldReturnOtherPlayersAvailabilityHours() {
		//when
		List<AvailabilityHoursEntity> hours = availabilityHoursRepository.getAvailabilityHoursOfOtherPlayers(1L);

		//then
		assertThat(hours.size()).isEqualTo(5);
		for (int i = 0; i < hours.size(); i++) {
			assertThat(hours.get(i).getPlayerId()).isNotEqualTo(1L);
		}
	}

	@Test
	public void shouldNotAddAvailabilityHours() {
		//given
		LocalTime fromHour = LocalTime.parse("10:00");
		LocalTime toHour = LocalTime.parse("13:00");

		//when
		Optional<AvailabilityHoursEntity> hours = availabilityHoursRepository
				.addAvailabilityHours(new AvailabilityHoursEntity(1L, fromHour, toHour));

		//then
		assertFalse(hours.isPresent());
	}

	@Test
	public void shouldAddAvailabilityHours() {
		//given
		LocalTime fromHour = LocalTime.parse("22:50");
		LocalTime toHour = LocalTime.parse("22:51");

		//when
		Optional<AvailabilityHoursEntity> hours = availabilityHoursRepository
				.addAvailabilityHours(new AvailabilityHoursEntity(1L, fromHour, toHour));

		//then
		assertTrue(hours.isPresent());
		assertThat(hours.get().getPlayerId()).isEqualTo(1L);
		assertThat(hours.get().getFromHour()).isEqualTo(fromHour);
		assertThat(hours.get().getToHour()).isEqualTo(toHour);
	}

	@Test
	public void shouldRemoveAvailabilityHours() {
		//given
		LocalTime fromHour = LocalTime.parse("22:55");
		LocalTime toHour = LocalTime.parse("22:56");
		AvailabilityHoursEntity hoursToRemove = new AvailabilityHoursEntity(1L, fromHour, toHour);
		availabilityHoursRepository.addAvailabilityHours(hoursToRemove);
		int listSize = availabilityHoursRepository.getAvailabilityHours().size();

		//when
		Optional<AvailabilityHoursEntity> hours = availabilityHoursRepository.removeAvailabilityHours(hoursToRemove);

		//then
		assertTrue(hours.isPresent());
		assertThat(hours.get().getPlayerId()).isEqualTo(1L);
		assertThat(hours.get().getFromHour()).isEqualTo(fromHour);
		assertThat(hours.get().getToHour()).isEqualTo(toHour);
		assertThat(listSize).isGreaterThan(availabilityHoursRepository.getAvailabilityHours().size());
	}

	@Test
	public void shouldNotRemoveAvailabilityHours() {
		//given
		LocalTime fromHour = LocalTime.parse("22:57");
		LocalTime toHour = LocalTime.parse("22:58");
		AvailabilityHoursEntity hoursToRemove = new AvailabilityHoursEntity(1L, fromHour, toHour);
		int listSize = availabilityHoursRepository.getAvailabilityHours().size();

		//when
		Optional<AvailabilityHoursEntity> hours = availabilityHoursRepository.removeAvailabilityHours(hoursToRemove);

		//then
		assertFalse(hours.isPresent());
		assertThat(listSize).isEqualTo(availabilityHoursRepository.getAvailabilityHours().size());
	}
}