package com.unir.payment.service.impl;

import com.unir.payment.repository.ItemPedidoRepository;
import com.unir.payment.service.ItemPedidoService;
import com.unir.payment.service.dto.ItemPedidoDTO;
import com.unir.payment.service.dto.PedidoDTO;
import com.unir.payment.service.mapper.ItemPedidoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private final Logger log = LoggerFactory.getLogger(ItemPedidoServiceImpl.class);

    private final ItemPedidoRepository itemPedidoRepository;

    private final ItemPedidoMapper itemPedidoMapper;

    @Autowired
    public ItemPedidoServiceImpl(ItemPedidoRepository itemPedidoRepository, ItemPedidoMapper itemPedidoMapper) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.itemPedidoMapper = itemPedidoMapper;
    }


    @Override
    public List<ItemPedidoDTO> saveAll(List<ItemPedidoDTO> items, PedidoDTO pedidoDTO) {
        log.debug("Request to save ItemPedido : {}", items);
        return items.stream().peek(item -> item.setPedido(pedidoDTO))
            .map(itemPedidoMapper::toEntity)
            .map(itemPedidoRepository::save)
            .map(itemPedidoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
