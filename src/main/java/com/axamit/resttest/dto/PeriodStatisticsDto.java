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

    private Integer amountAllVisits;
    private Integer amountUniqueUsers;
    private Integer amountRegularUsers;

    public static final RowMapper<PeriodStatisticsDto> ROW_MAPPER =
            (resultSet, i) -> PeriodStatisticsDto.builder()
                    .amountAllVisits(resultSet.getInt("amount_all_visits"))
                    .amountUniqueUsers(resultSet.getInt("amount_unique_users"))
                    .amountRegularUsers(resultSet.getInt("amount_regular_users"))
                    .build();
}
