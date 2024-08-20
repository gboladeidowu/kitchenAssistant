package io.github.gboladeidowu.kitchenassistant.repository;

import io.github.gboladeidowu.kitchenassistant.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    List<Inventory> findByDescription(String description);

    Optional<Inventory> findByDescriptionAndRecipeName(String description, String recipeName);

    boolean existsByDescriptionAndRecipeName(String description, String recipeName);

}
