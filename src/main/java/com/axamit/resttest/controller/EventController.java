package com.axamit.resttest.controller;

import com.axamit.resttest.dto.OneDayStatisticsDto;
import com.axamit.resttest.dto.PeriodStatisticsDto;
import com.axamit.resttest.form.VisitEventForm;
import com.axamit.resttest.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/add")
    public ResponseEntity<OneDayStatisticsDto> registerVisit(@RequestBody @Valid VisitEventForm event, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(eventService.addEvent(event));
        }
    }

    @GetMapping(value = "/statistics", params = {"from", "to"})
    public ResponseEntity<PeriodStatisticsDto> getStatistics(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        if (from.isBefore(to)) {
            return ResponseEntity.ok(eventService.getStatisticsForPeriod(from, to));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
