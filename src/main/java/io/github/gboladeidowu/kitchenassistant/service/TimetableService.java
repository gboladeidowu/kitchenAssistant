package io.github.gboladeidowu.kitchenassistant.service;


import io.github.gboladeidowu.kitchenassistant.dto.TimetableDto;
import io.github.gboladeidowu.kitchenassistant.model.Timetable;
import io.github.gboladeidowu.kitchenassistant.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TimetableService {

    private final TimetableRepository timetableRepository;

    //add meals
    public void addMeal(String day, TimetableDto timetableDTO) {
        // Check if day exists in timetable
        Optional<Timetable> existingTimetable = timetableRepository.findByDay(day.toLowerCase());
        if (existingTimetable.isPresent()) {
            // Get existing inventory object
            Timetable weekDay = existingTimetable.get();
                // set values for meals
                weekDay.setBreakfast(timetableDTO.breakfast().toLowerCase());
                weekDay.setLunch(timetableDTO.lunch().toLowerCase());
                weekDay.setDinner(timetableDTO.dinner().toLowerCase());

                // Save timetable
                timetableRepository.save(weekDay);
        }
        else {
            throw new IllegalStateException(String.format("Day: %s does not exist", day));
        }
    }

    //get all meals
    public List<TimetableDto> getMeals() {
        // return all timetable
        return timetableRepository.findAll()
                .stream().map(this::mapToTimetableDto).toList();
    }

    private TimetableDto mapToTimetableDto(Timetable timetable){
        // Capitalize first letters of meal
        String capitalizedBreakfast = timetable.getBreakfast().substring(0,1).toUpperCase()
                + timetable.getBreakfast().substring(1);
        String capitalizedLunch = timetable.getLunch().substring(0,1).toUpperCase()
                + timetable.getLunch().substring(1);
        String capitalizedDinner = timetable.getDinner().substring(0,1).toUpperCase()
                + timetable.getDinner().substring(1);

        // Return new TimetableDTO
        return TimetableDto.builder()
                .breakfast(capitalizedBreakfast)
                .lunch(capitalizedLunch)
                .dinner(capitalizedDinner)
                .build();
    }

}