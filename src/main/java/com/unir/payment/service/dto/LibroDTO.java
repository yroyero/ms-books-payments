package com.unir.payment.service.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroDTO implements Serializable {

    private Long id;

    private String title;

    private String author;

    private LocalDate releaseDate;

    private String genre;

    private String isbn;

    private Integer rating;

    private Boolean visible;

    private Double price;

    private String imageUrl;

    private String summary;

    private String tags;
}
