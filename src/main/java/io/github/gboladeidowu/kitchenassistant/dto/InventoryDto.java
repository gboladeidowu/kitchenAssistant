package io.github.gboladeidowu.kitchenassistant.dto;

import lombok.Builder;

@Builder
public record InventoryDto(
        String recipeName,
        Integer quantity,
        String unit) {
}
