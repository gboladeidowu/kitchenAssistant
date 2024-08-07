package io.github.gboladeidowu.kitchenassistant;

import io.github.gboladeidowu.kitchenassistant.model.Timetable;
import io.github.gboladeidowu.kitchenassistant.repository.TimetableRepository;
import org.apache.catalina.core.ApplicationContext;
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


   /* @Bean
    CommandLineRunner commandLineRunner(TimetableRepository timetableRepository, List<Timetable> timetables) {
        return args -> {
            Timetable monday = new Timetable("Monday",null,null,null);
            Timetable tuesday = new Timetable("Tuesday",null,null,null);
            Timetable wednesday = new Timetable("Wednesday",null,null,null);
            Timetable thursday = new Timetable("Thursday",null,null,null);
            Timetable friday = new Timetable("Friday",null,null,null);
            Timetable saturday = new Timetable("Saturday",null,null,null);
            Timetable sunday = new Timetable("Sunday",null,null,null);

            List<Timetable> days = new ArrayList<>(Arrays.asList(monday,tuesday,wednesday,thursday,friday,saturday,sunday));

            timetableRepository.saveAll(days);
        };
    }
*/
}
