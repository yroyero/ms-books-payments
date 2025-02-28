package com.unir.payment.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
public class LibroDTO implements Serializable {

    private String id;

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
