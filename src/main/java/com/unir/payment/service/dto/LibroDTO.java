package com.unir.payment.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class LibroDTO implements Serializable {

    private Long id;

    private String titulo;

    private String autor;

    private double precio;

    private boolean visible;
}
