package com.unir.payment.service.impl;

import com.unir.payment.repository.PedidoRepository;
import com.unir.payment.service.ItemPedidoService;
import com.unir.payment.service.LibroService;
import com.unir.payment.service.PedidoService;
import com.unir.payment.service.dto.ItemPedidoDTO;
import com.unir.payment.service.dto.LibroDTO;
import com.unir.payment.service.dto.PedidoDTO;
import com.unir.payment.service.mapper.PedidoMapper;
import com.unir.payment.web.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);

    private final PedidoMapper pedidoMapper;

    private final PedidoRepository pedidoRepository;

    private final ItemPedidoService itemPedidoService;

    private final LibroService libroService;

    @Autowired
    public PedidoServiceImpl(PedidoMapper pedidoMapper, PedidoRepository pedidoRepository,
                             ItemPedidoService itemPedidoService, LibroService libroService) {
        this.pedidoMapper = pedidoMapper;
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoService = itemPedidoService;
        this.libroService = libroService;
    }

    @Override
    public PedidoDTO save(PedidoDTO pedidoDTO) {
        log.debug("Request to save Pedido : {}", pedidoDTO);
        List<LibroDTO> libros = getLibros(pedidoDTO.getItems());
        boolean check = checkStock(libros, pedidoDTO.getItems());
        if (!check) {
            throw new NotFoundException("Algunos libros no tienen stock suficiente");
        }
        pedidoDTO.setEstado("PAGADO");
        PedidoDTO result = pedidoMapper.toDto(pedidoRepository.save(pedidoMapper.toEntity(pedidoDTO)));
        List<ItemPedidoDTO> items = itemPedidoService.saveAll(pedidoDTO.getItems(), result);
        result.setItems(items);
        return result;
    }

    @Override
    public PedidoDTO getPedidoDTO(Long pedidoId) {
        log.debug("Request to get PedidoDTO : {}", pedidoId);
        return pedidoMapper.toDto(pedidoRepository.findById(pedidoId).orElseThrow(() -> new NotFoundException("Pedido not found")));
    }

    @Override
    public List<PedidoDTO> getAllPedidos() {
        log.debug("Request to get Pedidos");
        return pedidoRepository.findAll().stream().map(pedidoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    private List<LibroDTO> getLibros(List<ItemPedidoDTO> items) {
        return items.stream().map(item -> libroService
                .findLibroById(item.getLibroId()).orElseThrow()).collect(Collectors.toCollection(LinkedList::new));
    }

    private boolean checkStock(List<LibroDTO> libros, List<ItemPedidoDTO> items) {
        return libros.stream().allMatch(libro -> items.stream()
                .anyMatch(item -> item.getLibroId().equals(libro.getId()) && libro.getVisible()));
    }
}
