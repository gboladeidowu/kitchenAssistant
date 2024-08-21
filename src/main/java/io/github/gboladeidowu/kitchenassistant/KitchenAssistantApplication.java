package io.github.gboladeidowu.kitchenassistant;

import io.github.gboladeidowu.kitchenassistant.model.Timetable;
import io.github.gboladeidowu.kitchenassistant.repository.TimetableRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class KitchenAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(KitchenAssistantApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(TimetableRepository timetableRepository) {
        return args -> {
            List<Timetable> timetable = new ArrayList<>();
            List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
            for (String day : days) {
                if (timetableRepository.findByDay(day.toLowerCase()).isPresent())
                    return;

                Timetable weekDay = new Timetable();
                weekDay.setDay(day.toLowerCase());
                timetable.add(weekDay);
            }
            timetableRepository.saveAll(timetable);

        };
    }
}
