package io.github.gboladeidowu.kitchenassistant.controller;


import io.github.gboladeidowu.kitchenassistant.dto.GetInventoryDTO;
import io.github.gboladeidowu.kitchenassistant.dto.InventoryDTO;
import io.github.gboladeidowu.kitchenassistant.model.Inventory;
import io.github.gboladeidowu.kitchenassistant.model.InventoryDescription;
import io.github.gboladeidowu.kitchenassistant.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{description}")
    public List<GetInventoryDTO> getAllInventory(@PathVariable InventoryDescription description){
        return inventoryService.getAllInventory(description);
    }

    @GetMapping("/{description}/param")
    public Optional<Inventory> getByParam(@PathVariable InventoryDescription description,
                                          @RequestParam(required = false) String recipeName,
                                          @RequestParam(required = false) Integer quantity){
        return inventoryService.getByParam(description, recipeName, quantity);
    }

    @PostMapping("/{description}")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveInventory(@PathVariable InventoryDescription description, @RequestBody InventoryDTO inventoryDTO){
        inventoryService.saveInventory(description, inventoryDTO);
        return "Inventory saved";
    }

    @PutMapping("/{description}/{recipeName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateInventory(@PathVariable InventoryDescription description,
                                  @PathVariable String recipeName,
                                  @RequestBody InventoryDTO inventoryDTO) {
        inventoryService.updateInventory(description, recipeName, inventoryDTO);
        return "Inventory updated";
    }

    @DeleteMapping("/{description}/{recipeName}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteInventory(@PathVariable InventoryDescription description, @PathVariable String recipeName){
        inventoryService.deleteInventory(description, recipeName);
        return "Inventory deleted";
    }
}