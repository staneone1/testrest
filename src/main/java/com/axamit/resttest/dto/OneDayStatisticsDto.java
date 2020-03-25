package com.axamit.resttest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OneDayStatisticsDto {

    private Integer amountAllVisits;
    private Integer amountUniqueUsers;

    public static final RowMapper<OneDayStatisticsDto> ROW_MAPPER =
            (resultSet, i) -> OneDayStatisticsDto.builder()
                    .amountAllVisits(resultSet.getInt("amount_all_visits"))
                    .amountUniqueUsers(resultSet.getInt("amount_unique_users"))
                    .build();
}
