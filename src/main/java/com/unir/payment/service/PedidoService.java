package com.unir.payment.service;

import com.unir.payment.service.dto.PedidoDTO;

public interface PedidoService {

    PedidoDTO save(PedidoDTO pedidoDTO);

    PedidoDTO getPedidoDTO(Long pedidoId);


}
