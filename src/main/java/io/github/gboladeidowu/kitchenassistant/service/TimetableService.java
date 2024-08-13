package io.github.gboladeidowu.kitchenassistant.service;


import io.github.gboladeidowu.kitchenassistant.dto.TimetableDTO;
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
    public void addMeal(String day, TimetableDTO timetableDTO) {
        // Check if day exists in timetable
        Optional<Timetable> existingTimetable = timetableRepository.findByDayIgnoreCase(day);
        if (existingTimetable.isPresent()) {
            // Capitalize first letter of meal
            String capitalizedBreakFast = timetableDTO.breakfast().substring(0, 1).toUpperCase()
                    + timetableDTO.breakfast().substring(1);
            String capitalizedLunch = timetableDTO.lunch().substring(0, 1).toUpperCase()
                    + timetableDTO.lunch().substring(1);
            String capitalizedDinner = timetableDTO.dinner().substring(0, 1).toUpperCase()
                    + timetableDTO.dinner().substring(1);

            // Get existing inventory object
            Timetable weekDay = existingTimetable.get();

                // set values for meals
                weekDay.setBreakfast(capitalizedBreakFast);
                weekDay.setLunch(capitalizedLunch);
                weekDay.setDinner(capitalizedDinner);

                // Save timetable
                timetableRepository.save(weekDay);
        }
        else {
            throw new IllegalStateException(String.format("Day: %s does not exist", day));
        }
    }

    //get all meals
    public List<Timetable> getMeals() {
        // return all timetable
        return timetableRepository.findAll();
    }

}