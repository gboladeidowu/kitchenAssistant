package io.github.gboladeidowu.kitchenassistant.dto;

import lombok.Builder;

@Builder
public record GetInventoryDTO (String recipeName,
                               Integer quantity,
                               double unit){
}
