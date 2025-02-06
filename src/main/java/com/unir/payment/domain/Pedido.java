package com.unir.payment.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fecha")
    private ZonedDateTime fecha;

    @Column(name = "monto")
    private Double montoTotal;

    @Column(name = "descuento")
    private Double descuento;

    @Column(name = "estado")
    private String estado;

    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<ItemPedido> items;

}
