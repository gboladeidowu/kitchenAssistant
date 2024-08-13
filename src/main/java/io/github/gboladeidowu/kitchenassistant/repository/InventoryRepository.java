package io.github.gboladeidowu.kitchenassistant.repository;

import io.github.gboladeidowu.kitchenassistant.model.Inventory;
import io.github.gboladeidowu.kitchenassistant.model.InventoryDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    void deleteByRecipeNameIgnoreCase(String recipeName);

    List<Inventory> findByDescription(InventoryDescription description);
}
