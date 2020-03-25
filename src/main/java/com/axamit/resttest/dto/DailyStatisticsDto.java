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
public class DailyStatisticsDto {

    private Integer amountAllVisits;
    private Integer amountUniqueUsers;

    public static final RowMapper<DailyStatisticsDto> ROW_MAPPER =
            (resultSet, i) -> DailyStatisticsDto.builder()
                    .amountAllVisits(resultSet.getInt("amount_all_visits"))
                    .amountUniqueUsers(resultSet.getInt("amount_unique_users"))
                    .build();

}
