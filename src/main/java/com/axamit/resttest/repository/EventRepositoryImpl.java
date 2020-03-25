package com.axamit.resttest.repository;

import com.axamit.resttest.dto.OneDayStatisticsDto;
import com.axamit.resttest.dto.PeriodStatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private static final String INSERT_EVENT_QUERY =
            "INSERT INTO visit_events(user_id, page_id, timestamp) VALUES (:user_id, :page_id, :timestamp);";
    private static final String SELECT_STATISTICS_FOR_ONE_DAY =
            "SELECT count(user_id) AS amount_all_visits, count(DISTINCT user_id) AS amount_unique_users " +
            "FROM visit_events " +
            "WHERE timestamp::DATE = now()::DATE AND page_id = :page_id;";
    private static final String SELECT_PERIOD_STATISTICS_QUERY =
            "SELECT " +
                    "  sum(statistics.amount_all_visits) AS amount_all_visits, " +
                    "  sum(statistics.amount_unique_users) AS amount_unique_users, " +
                    "  sum(CASE WHEN statistics.visited_pages > 10 THEN 1 ELSE 0 END) AS amount_regular_users " +
                    "FROM (" +
                    "       SELECT count(user_id)          AS amount_all_visits, " +
                    "              count(DISTINCT user_id) AS amount_unique_users, " +
                    "              count(DISTINCT page_id) AS visited_pages " +
                    "       FROM visit_events " +
                    "       WHERE timestamp >= :from AND timestamp <= :to " +
                    "       GROUP BY user_id " +
                    "     ) AS statistics;";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public EventRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public OneDayStatisticsDto addEvent(Long userId, Long pageId, Timestamp timestamp) {

        Map<String, Object> queryParams = new HashMap<>();

        queryParams.put("user_id", userId);
        queryParams.put("page_id", pageId);
        queryParams.put("timestamp", timestamp);

        jdbcTemplate.update(INSERT_EVENT_QUERY, queryParams);

        return jdbcTemplate.queryForObject(SELECT_STATISTICS_FOR_ONE_DAY, queryParams, OneDayStatisticsDto.ROW_MAPPER);
    }

    @Override
    public PeriodStatisticsDto getStatisticsForPeriod(Timestamp from, Timestamp to) {

        Map<String, Timestamp> queryParams = new HashMap<>();

        queryParams.put("from", from);
        queryParams.put("to", to);

        return jdbcTemplate.queryForObject(SELECT_PERIOD_STATISTICS_QUERY, queryParams, PeriodStatisticsDto.ROW_MAPPER);
    }
}
