package com.axamit.resttest.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitEventForm {

    @NotNull
    private Long userId;
    @NotNull
    private Long pageId;
}
