package io.github.gboladeidowu.kitchenassistant.dto;

import lombok.Builder;

@Builder
public record InventoryDTO(
        String recipeName,
        Integer quantity,
        String unit) {
}
