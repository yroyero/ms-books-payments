package com.unir.payment.service.dto;

import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO implements Serializable {

    private Long id;

    private ZonedDateTime fecha;

    private Double montoTotal;

    private Double descuento;

    private String estado;

    private String nombreCliente;

    private List<ItemPedidoDTO> items;
}
