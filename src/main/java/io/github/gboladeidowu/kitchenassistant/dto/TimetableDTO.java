package io.github.gboladeidowu.kitchenassistant.dto;


import lombok.Builder;

@Builder
public record TimetableDTO(
        String breakfast,
        String lunch,
        String dinner){
}
