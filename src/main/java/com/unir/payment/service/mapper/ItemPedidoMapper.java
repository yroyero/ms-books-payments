package com.unir.payment.service.mapper;

import com.unir.payment.domain.ItemPedido;
import com.unir.payment.domain.Pedido;
import com.unir.payment.service.dto.ItemPedidoDTO;
import com.unir.payment.service.dto.PedidoDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper extends EntityMapper<ItemPedidoDTO, ItemPedido> {

    @Mapping(source = "pedido", target = "pedido", qualifiedByName = "pedidoId")
    ItemPedidoDTO toDto(ItemPedido itemPedido);


    @Named("pedidoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PedidoDTO toDtoPedidoId(Pedido pedido);

}
