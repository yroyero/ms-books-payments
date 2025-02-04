package com.unir.payment.service.mapper;

import com.unir.payment.domain.Pedido;
import com.unir.payment.service.dto.PedidoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ItemPedidoMapper.class})
public interface PedidoMapper extends EntityMapper<PedidoDTO, Pedido> {
}
