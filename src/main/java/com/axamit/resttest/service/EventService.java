package com.axamit.resttest.service;

import com.axamit.resttest.dto.OneDayStatisticsDto;
import com.axamit.resttest.dto.PeriodStatisticsDto;
import com.axamit.resttest.form.VisitEventForm;

import java.time.LocalDate;

public interface EventService {

    OneDayStatisticsDto addEvent(VisitEventForm dto);
    PeriodStatisticsDto getStatisticsForPeriod(LocalDate from, LocalDate to);
}
