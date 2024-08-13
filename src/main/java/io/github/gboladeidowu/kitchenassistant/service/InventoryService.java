package io.github.gboladeidowu.kitchenassistant.service;

import io.github.gboladeidowu.kitchenassistant.dto.GetInventoryDTO;
import io.github.gboladeidowu.kitchenassistant.dto.InventoryDTO;
import io.github.gboladeidowu.kitchenassistant.model.Inventory;
import io.github.gboladeidowu.kitchenassistant.model.InventoryDescription;
import io.github.gboladeidowu.kitchenassistant.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    //save inventory
    public void saveInventory(InventoryDescription description, InventoryDTO inventoryDTO) {
        // Check if an inventory item with the same recipe name already exists
        Optional<Inventory> existingInventory = inventoryRepository.findByDescription(description)
                .stream()
                .filter(inventory -> inventory.getRecipeName().equalsIgnoreCase(inventoryDTO.recipeName())).findFirst();

        if (existingInventory.isEmpty()){
            // Capitalize first letter of recipe name
            String capitalizedRecipeName = inventoryDTO.recipeName().substring(0,1).toUpperCase()
                    + inventoryDTO.recipeName().substring(1);

            // Create a new inventory object
            Inventory inventory = Inventory.builder()
                    .recipeName(capitalizedRecipeName)
                    .quantity(inventoryDTO.quantity())
                    .unit(inventoryDTO.unit())
                    .build();

            // Set description
            switch (description) {
                case Cooked:
                    inventory.setDescription(InventoryDescription.Cooked);
                    break;

                case Uncooked:
                    inventory.setDescription(InventoryDescription.Uncooked);
                    break;
                default: throw new IllegalArgumentException(String.format("Unknown description: '%s'",description));
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
    public void updateInventory(InventoryDescription description, String recipeName, InventoryDTO inventoryDTO){
        // Check if inventory item with recipe name exists
        Optional<Inventory> existingInventory = inventoryRepository.findByDescription(description)
                .stream()
                .filter(inventory -> inventory.getRecipeName().equalsIgnoreCase(recipeName)).findFirst();

        if (existingInventory.isPresent()){
            // Capitalize first letter of recipe name
            String capitalizedRecipeName = inventoryDTO.recipeName().substring(0,1).toUpperCase()
                    + inventoryDTO.recipeName().substring(1);

            // Get existing inventory object
            Inventory inventory = existingInventory.get();
            // Use setters to set new values
            inventory.setRecipeName(capitalizedRecipeName);
            inventory.setQuantity(inventoryDTO.quantity());
            inventory.setUnit(inventoryDTO.unit());

            // Save updated inventory object
            inventoryRepository.save(inventory);
        }
        else {
            throw new IllegalStateException(String.format("Inventory with recipe name: '%s' does not exist.", recipeName));
        }
    }


    //delete inventory
    public void deleteInventory(InventoryDescription description, String recipeName){
        Optional<Inventory> existingInventory = inventoryRepository.findByDescription(description)
                .stream()
                .filter(inventory -> inventory.getRecipeName().equalsIgnoreCase(recipeName)).findFirst();

        if (existingInventory.isPresent()){
            inventoryRepository.deleteByRecipeNameIgnoreCase(recipeName);
        }
        else{
            throw new IllegalStateException(String.format("Recipe: '%s' does not exist.", recipeName));
        }
    }


    //get inventory by cooked or uncooked
    public List<GetInventoryDTO> getAllInventory(InventoryDescription description){
        // return inventories based on description
        return inventoryRepository.findByDescription(description).stream().map(this::mapInventoryTODTO).toList();
    }

    //map inventory to GetInventoryDTO
    private GetInventoryDTO mapInventoryTODTO(Inventory inventory){
       return GetInventoryDTO.builder()
               .recipeName(inventory.getRecipeName())
               .quantity(inventory.getQuantity())
               .unit(inventory.getUnit())
               .build();
    }

    //find by either name, quantity or unit
    public Optional<Inventory> getByParam(InventoryDescription description, String recipeName, Integer quantity) throws IllegalStateException{
        // return inventory that matches the value for the query
        return inventoryRepository.findByDescription(description)
                .stream()
                .filter(inventory -> inventory.getRecipeName().toLowerCase().contains(recipeName.toLowerCase()))
                .filter(inventory -> inventory.getQuantity().equals(quantity))
                .findAny();
    }
}
