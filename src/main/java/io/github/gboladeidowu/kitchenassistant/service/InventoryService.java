package io.github.gboladeidowu.kitchenassistant.service;

import io.github.gboladeidowu.kitchenassistant.dto.InventoryDto;
import io.github.gboladeidowu.kitchenassistant.model.Inventory;
import io.github.gboladeidowu.kitchenassistant.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    //save inventory
    public void saveInventory(String description, InventoryDto inventoryDTO) {
        // Check if an inventory item with the same recipe name already exists
        Optional<Inventory> existingInventory = inventoryRepository.findByDescription(description)
                .stream()
                .filter(inventory -> inventory.getRecipeName().equalsIgnoreCase(inventoryDTO.recipeName()))
                .findFirst();

        if (existingInventory.isEmpty()) {
            // Create a new inventory object
            Inventory inventory = Inventory.builder()
                    .recipeName(inventoryDTO.recipeName().toLowerCase())
                    .quantity(inventoryDTO.quantity())
                    .unit(inventoryDTO.unit().toLowerCase())
                    .build();

            // Set description
            switch (description) {
                case "cooked":
                    inventory.setDescription("cooked");
                    break;

                case "uncooked":
                    inventory.setDescription("uncooked");
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Unknown description: '%s'", description));
            }
            // Save the new inventory object
            inventoryRepository.save(inventory);
        } else {
            // Throw an exception if the inventory item already exists
            throw new IllegalStateException(String.format("Inventory with recipe name: '%s' already exists. " +
                    "Choose another name or update the quantity/unit", inventoryDTO.recipeName()));
        }
    }


    //update inventory
    public void updateInventory(String description, String recipeName, InventoryDto inventoryDTO) {
        // Check if inventory item with recipe name exists
        Optional<Inventory> existingInventory = inventoryRepository.findByDescription(description)
                .stream()
                .filter(inventory -> inventory.getRecipeName().equalsIgnoreCase(recipeName))
                .findFirst();

        if (existingInventory.isPresent()) {
            // Get existing inventory object
            Inventory inventory = existingInventory.get();
            // Use setters to set new values
            inventory.setRecipeName(inventoryDTO.recipeName().toLowerCase());
            inventory.setQuantity(inventoryDTO.quantity());
            inventory.setUnit(inventoryDTO.unit().toLowerCase());

            // Save updated inventory object
            inventoryRepository.save(inventory);
        } else {
            throw new IllegalStateException(String.format("Inventory with recipe name: '%s' does not exist.", recipeName));
        }
    }


    //delete inventory
    public void deleteInventory(String description, String recipeName) {
        Optional<Inventory> existingInventory = inventoryRepository.findByDescription(description)
                .stream()
                .filter(inventory -> inventory.getRecipeName().equalsIgnoreCase(recipeName)).findFirst();

        if (existingInventory.isPresent()) {
            inventoryRepository.deleteByRecipeName(recipeName.toLowerCase());
        } else {
            throw new IllegalStateException(String.format("Recipe: '%s' does not exist.", recipeName));
        }
    }

    //map inventory to InventoryDto
    private InventoryDto mapToInventoryDto(Inventory inventory) {
        // Capitalize first letters of values
        String capitalizedRecipeName = inventory.getRecipeName().substring(0, 1).toUpperCase()
                + inventory.getRecipeName().substring(1);
        String capitalizedUnit = inventory.getUnit().substring(0, 1).toUpperCase()
                + inventory.getUnit().substring(1);
        // Return new GetInventoryDTO
        return InventoryDto.builder()
                .recipeName(capitalizedRecipeName)
                .quantity(inventory.getQuantity())
                .unit(capitalizedUnit)
                .build();
    }

    //find all or by recipe name
    public List<InventoryDto> getByParam(String description, String param) {
        // return inventory that matches the value for the query
        return inventoryRepository.findByDescription(description)
                .stream()
                .filter(inventory -> param == null
                        || inventory.getRecipeName().toLowerCase().contains(param.toLowerCase())
                || inventory.getUnit().toLowerCase().contains(param.toLowerCase()))
                .map(this::mapToInventoryDto).toList();
    }
}
