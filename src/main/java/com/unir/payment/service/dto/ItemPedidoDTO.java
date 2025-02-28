package com.unir.payment.service.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ItemPedidoDTO implements Serializable {

    public Long id;

    public Long cantidad;

    public String libroId;

    public Double monto;

    public PedidoDTO pedido;
}
