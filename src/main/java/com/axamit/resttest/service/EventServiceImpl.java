package com.axamit.resttest.service;

import com.axamit.resttest.dto.OneDayStatisticsDto;
import com.axamit.resttest.dto.PeriodStatisticsDto;
import com.axamit.resttest.form.VisitEventForm;
import com.axamit.resttest.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public OneDayStatisticsDto addEvent(VisitEventForm dto) {
        return eventRepository.addEvent(dto.getUserId(), dto.getPageId(), Timestamp.from(Instant.now()));
    }

    @Override
    public PeriodStatisticsDto getStatisticsForPeriod(LocalDate from, LocalDate to) {
        return eventRepository.getStatisticsForPeriod(Timestamp.valueOf(from.atStartOfDay()), Timestamp.valueOf(to.atTime(LocalTime.MAX)));
    }
}
