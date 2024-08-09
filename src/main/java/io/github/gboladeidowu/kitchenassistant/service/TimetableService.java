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

    //save meals
    public void saveMeal(String day, TimetableDTO timetableDTO) {
        Optional<Timetable> timetable = timetableRepository.findByDayIgnoreCase(day);
        if (timetable.isPresent()) {
            Timetable weekDay = timetable.get();
                weekDay.setBreakfast(timetableDTO.breakfast());
                weekDay.setLunch(timetableDTO.lunch());
                weekDay.setDinner(timetableDTO.dinner());

                timetableRepository.save(weekDay);
        }
        else throw new IllegalStateException(String.format("Day: %s does not exist", day));
    }

    //get all meals
    public List<Timetable> getMeals() {
        return timetableRepository.findAll();
    }

}