package com.unir.payment.web.rest;

import com.unir.payment.service.PedidoService;
import com.unir.payment.service.dto.PedidoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoResource {

    private final Logger log = LoggerFactory.getLogger(PedidoResource.class);

    private final PedidoService pedidoService;

    public PedidoResource(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @PostMapping("")
    public ResponseEntity<PedidoDTO> crearPedido(@RequestBody PedidoDTO pedidoDTO) {
        log.debug("REST request to save Pedido : {}", pedidoDTO);

        return ResponseEntity.ok(pedidoService.save(pedidoDTO));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedido(@PathVariable Long id) {
        log.debug("REST request to get Pedido : {}", id);
        return ResponseEntity.ok(pedidoService.getPedidoDTO(id));
    }

    @GetMapping("")
    public ResponseEntity<List<PedidoDTO>> getAllPedidos() {
        log.debug("REST request to get Pedidos ");
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }
}
