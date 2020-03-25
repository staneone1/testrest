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
public class PeriodStatisticsDto {

    private Long amountAllVisits;
    private Long amountUniqueUsers;
    private Long amountRegularUsers;

    public static final RowMapper<PeriodStatisticsDto> ROW_MAPPER =
            (resultSet, i) -> PeriodStatisticsDto.builder()
                    .amountAllVisits(resultSet.getLong("amount_all_visits"))
                    .amountUniqueUsers(resultSet.getLong("amount_unique_users"))
                    .amountRegularUsers(resultSet.getLong("amount_regular_users"))
                    .build();
}
