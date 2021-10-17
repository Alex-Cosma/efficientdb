package com.lab4.demo.item.model;

import com.lab4.demo.review.model.Review;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.lab4.demo.Utils.randomString;
import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String name;

    @Column(length = 1024)
    private String description;

    @Column
    private String statusCode;

    @Column
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "item", fetch = LAZY)
    @Builder.Default
    private Set<Review> reviews = new HashSet<>();

    @Column(length = 512, nullable = false)
    @Builder.Default
    private String filler1 = randomString();
    @Column(length = 512, nullable = false)
    @Builder.Default
    private String filler2 = randomString();
    @Column(length = 512, nullable = false)
    @Builder.Default
    private String filler3 = randomString();
    @Column(length = 512, nullable = false)
    @Builder.Default
    private String filler4 = randomString();
    @Column(length = 512, nullable = false)
    @Builder.Default
    private String filler5 = randomString();
    @Column(length = 512, nullable = false)
    @Builder.Default
    private String filler6 = randomString();
    @Column(length = 512, nullable = false)
    @Builder.Default
    private String filler7 = randomString();
    @Column(length = 512, nullable = false)
    @Builder.Default
    private String filler8 = randomString();
    @Column(length = 512, nullable = false)
    @Builder.Default
    private String filler9 = randomString();
    @Column(length = 512, nullable = false)
    @Builder.Default
    private String filler10 = randomString();
}
