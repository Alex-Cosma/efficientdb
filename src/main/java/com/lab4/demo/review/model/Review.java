package com.lab4.demo.review.model;

import com.lab4.demo.item.model.Item;
import com.lab4.demo.review.Rating;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private Rating rating;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
