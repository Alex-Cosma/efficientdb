package com.lab4.demo.item.model.dto;

import com.lab4.demo.item.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private String name;
    private String description;
    private double averageRating;

    public static ItemDTO fromEntity(Item item, String translation, double averageRating) {
        return ItemDTO.builder()
                .id(item.getId())
                .name(translation)
                .description(item.getDescription())
                .averageRating(averageRating)
                .build();
    }
}
