package io.github.gboladeidowu.kitchenassistant.controller;


import io.github.gboladeidowu.kitchenassistant.dto.TimetableDTO;
import io.github.gboladeidowu.kitchenassistant.model.Timetable;
import io.github.gboladeidowu.kitchenassistant.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/days")
public class TimetableController {

    private final TimetableService timetableService;

    @PostMapping("/{day}")
    @ResponseStatus(HttpStatus.OK)
    public String saveMeal(@PathVariable String day, @RequestBody TimetableDTO timetableDTO) {
        timetableService.saveMeal(day, timetableDTO);
        return"Meal saved.";
    }

    @GetMapping
    public List<Timetable> getMeals(){
        return timetableService.getMeals();
    }
}
