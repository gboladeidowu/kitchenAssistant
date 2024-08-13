package io.github.gboladeidowu.kitchenassistant.dto;


public record InventoryDTO(
        String recipeName,
        Integer quantity,
        double unit){
}
