package io.github.gboladeidowu.kitchenassistant.repository;


import io.github.gboladeidowu.kitchenassistant.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, String> {
    Optional<Timetable> findByDayIgnoreCase(String day);

    boolean existsByDayIgnoreCase(String day);
}
