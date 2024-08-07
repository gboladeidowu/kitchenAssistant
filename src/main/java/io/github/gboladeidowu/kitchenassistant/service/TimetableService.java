package io.github.gboladeidowu.kitchenassistant.service;


import io.github.gboladeidowu.kitchenassistant.dto.TimetableDTO;
import io.github.gboladeidowu.kitchenassistant.model.Timetable;
import io.github.gboladeidowu.kitchenassistant.repository.TimetableRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class TimetableService {

    private final TimetableRepository timetableRepository;

    //save meal for a day
    public void saveMeal(String day, TimetableDTO timetableDTO) {
        Optional<Timetable> timetable = timetableRepository.findByDayIgnoreCase(day);
        Timetable meal = Timetable.builder()
                .day(day)
                .breakfast(timetableDTO.breakfast())
                .lunch(timetableDTO.lunch())
                .dinner(timetableDTO.dinner())
                .build();
        if (timetable.isPresent()) {
            timetableRepository.deleteByDayIgnoreCase(day);
            timetableRepository.save(meal);
       }
       else timetableRepository.save(meal);
    }

    //get all meals
    public List<Timetable> getMeals() {
        return timetableRepository.findAll();
    }
}
