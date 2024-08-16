package io.github.gboladeidowu.kitchenassistant.controller;


import io.github.gboladeidowu.kitchenassistant.dto.InventoryDto;
import io.github.gboladeidowu.kitchenassistant.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{description}")
    public List<InventoryDto> getByParam(@PathVariable String description,
                                         @RequestParam(required = false) String param) {
        return inventoryService.getByParam(description, param);
    }

    @PostMapping("/{description}")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveInventory(@PathVariable String description, @RequestBody InventoryDto inventoryDTO) {
        inventoryService.saveInventory(description, inventoryDTO);
        return "Inventory saved";
    }

    @PutMapping("/{description}/{recipeName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateInventory(@PathVariable String description,
                                  @PathVariable String recipeName,
                                  @RequestBody InventoryDto inventoryDTO) {
        inventoryService.updateInventory(description, recipeName, inventoryDTO);
        return "Inventory updated";
    }

    @DeleteMapping("/{description}/{recipeName}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteInventory(@PathVariable String description, @PathVariable String recipeName) {
        inventoryService.deleteInventory(description, recipeName);
        return "Inventory deleted";
    }
}