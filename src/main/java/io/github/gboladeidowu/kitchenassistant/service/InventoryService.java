package io.github.gboladeidowu.kitchenassistant.service;

import io.github.gboladeidowu.kitchenassistant.dto.InventoryDto;
import io.github.gboladeidowu.kitchenassistant.model.Inventory;
import io.github.gboladeidowu.kitchenassistant.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    //save inventory
    public void saveInventory(String description, InventoryDto inventoryDTO) {
        // Check if an inventory item with the same recipe name already exists
       boolean existingInventory = inventoryRepository.existsByDescriptionAndRecipeName(description,inventoryDTO.recipeName().toLowerCase());

       if (!existingInventory) {
           Inventory inventory = Inventory.builder()
                   .recipeName(inventoryDTO.recipeName().toLowerCase())
                   .quantity(inventoryDTO.quantity())
                   .unit(inventoryDTO.unit().toLowerCase())
                   .build();

            // Set description
            switch (description.toLowerCase()) {
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
        }

       else {
            // Throw an exception if the inventory item already exists
            throw new IllegalStateException(String.format("Inventory with recipe name: '%s' already exists. " +
                "Choose another name or update the quantity/unit", inventoryDTO.recipeName()));
        }
    }


    //update inventory
    public void updateInventory(String description, String recipeName, InventoryDto inventoryDTO) {
        // Check if inventory item with recipe name exists
        Inventory existingInventory = inventoryRepository.findByDescriptionAndRecipeName(description, recipeName.toLowerCase())
                .orElseThrow(() -> new IllegalStateException(String.format("Inventory with recipe name: '%s' does not exist in %s inventory.", recipeName, description)));

            // Use setters to set new values
            existingInventory.setRecipeName(inventoryDTO.recipeName().toLowerCase());
            existingInventory.setQuantity(inventoryDTO.quantity());
            existingInventory.setUnit(inventoryDTO.unit().toLowerCase());

            // Save updated inventory object
            inventoryRepository.save(existingInventory);
    }


    //delete inventory
    public void deleteInventory(String description, String recipeName) {
       Inventory existingInventory = inventoryRepository.findByDescriptionAndRecipeName(description, recipeName)
               .orElseThrow(() -> new  IllegalStateException(String.format("Recipe with name: '%s' does not exist in %s inventory.", recipeName, description)));

       inventoryRepository.delete(existingInventory);

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
    public List<InventoryDto> getByParam(String description, String recipeName) {
        // return inventory that matches the value for the query
        return inventoryRepository
                .findByDescription(description)
                .stream()
                .filter(existingInventory -> recipeName == null || existingInventory.getRecipeName().toLowerCase()
                        .contains(recipeName.toLowerCase()))
                .map(this::mapToInventoryDto).toList();
    }
}
