package com.unir.payment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cantidad")
    private Long cantidad;

    @Column(name = "libro_id")
    private Long libroId;

    @Column(name = "monto")
    private Double monto;

    @ManyToOne
    @JsonIgnoreProperties(value = {"items"}, allowSetters = true)
    private Pedido pedido;
}
