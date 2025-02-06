package com.unir.payment.web.rest;

import com.unir.payment.service.PedidoService;
import com.unir.payment.service.dto.PedidoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
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

    @Operation(
            operationId = "Pagar pedido",
            description = "Operación de escritura",
            summary = "Se crea un pedido con los libros seleccionados y el monto pagado.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del pedido a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDTO.class))))
    @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = PedidoDTO.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Error en los datos proporcionados para pagar un pedido")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No existen los libros a comprar.")
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No hay stock suficiente de algunos libros.")
    @PostMapping("")
    public ResponseEntity<PedidoDTO> crearPedido(@RequestBody @Valid PedidoDTO pedidoDTO) {
        log.debug("REST request to save Pedido : {}", pedidoDTO);

        return ResponseEntity.ok(pedidoService.save(pedidoDTO));
    }

    @Operation(
            operationId = "Obtener un pedido",
            description = "Operacion de lectura",
            summary = "Se devuelve un pedido a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDTO.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el pedido con el identificador indicado.")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedido(@PathVariable Long id) {
        log.debug("REST request to get Pedido : {}", id);
        return ResponseEntity.ok(pedidoService.getPedidoDTO(id));
    }

    @Operation(
            operationId = "Obtener pedidos",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos los pedidos almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDTO.class)))
    @GetMapping("")
    public ResponseEntity<List<PedidoDTO>> getAllPedidos() {
        log.debug("REST request to get Pedidos");
        List<PedidoDTO> pedidos = pedidoService.getAllPedidos();
        if(ObjectUtils.isEmpty(pedidos)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidos);
    }
}
