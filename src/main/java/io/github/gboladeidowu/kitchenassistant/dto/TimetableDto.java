package io.github.gboladeidowu.kitchenassistant.dto;


import lombok.Builder;

@Builder
public record TimetableDto(
        String breakfast,
        String lunch,
        String dinner){
}
