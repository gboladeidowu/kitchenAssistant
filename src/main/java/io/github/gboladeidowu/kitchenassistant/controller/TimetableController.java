package io.github.gboladeidowu.kitchenassistant.controller;


import io.github.gboladeidowu.kitchenassistant.dto.TimetableDto;
import io.github.gboladeidowu.kitchenassistant.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/timetable")
public class TimetableController {

    private final TimetableService timetableService;

    @PutMapping("/{day}")
    @ResponseStatus(HttpStatus.CREATED)
    public String addMeal(@PathVariable String day, @RequestBody TimetableDto timetableDTO) {
        timetableService.addMeal(day, timetableDTO);
        return"Meal saved.";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TimetableDto> getMeals(){
        return timetableService.getMeals();
    }
}
