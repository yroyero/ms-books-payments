package com.unir.payment.service;

import com.unir.payment.service.dto.ItemPedidoDTO;
import com.unir.payment.service.dto.PedidoDTO;

import java.util.List;

public interface ItemPedidoService {
    List<ItemPedidoDTO> saveAll(List<ItemPedidoDTO> items, PedidoDTO pedidoDTO);
}
