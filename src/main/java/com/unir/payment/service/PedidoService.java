package com.unir.payment.service;

import com.unir.payment.service.dto.PedidoDTO;

import java.util.List;

public interface PedidoService {

    PedidoDTO save(PedidoDTO pedidoDTO);

    PedidoDTO getPedidoDTO(Long pedidoId);

    List<PedidoDTO> getAllPedidos();

}
