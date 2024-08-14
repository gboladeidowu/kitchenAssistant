package io.github.gboladeidowu.kitchenassistant.repository;

import io.github.gboladeidowu.kitchenassistant.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    void deleteByRecipeName(String recipeName);

    List<Inventory> findByDescription(String description);
}
