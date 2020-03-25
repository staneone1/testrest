package com.axamit.resttest.repository;

import com.axamit.resttest.dto.OneDayStatisticsDto;
import com.axamit.resttest.dto.PeriodStatisticsDto;

import java.sql.Timestamp;

public interface EventRepository {

    OneDayStatisticsDto addEvent(Integer userId, Integer pageId, Timestamp timestamp);
    PeriodStatisticsDto getStatisticsForPeriod(Timestamp from, Timestamp to);
}
