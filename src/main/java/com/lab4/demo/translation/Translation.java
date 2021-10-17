package com.lab4.demo.translation;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String code;

    @Column(length = 512, nullable = false)
    private String language;

    @Column(length = 512, nullable = false)
    private String translation;
}
