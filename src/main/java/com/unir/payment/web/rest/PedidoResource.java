package com.unir.payment.web.rest;

import com.unir.payment.service.PedidoService;
import com.unir.payment.service.dto.PedidoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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

    @Operation(operationId = "Pagar pedido", description = "Operación de escritura", summary = "Se crea un pedido con los libros seleccionados y el monto pagado.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del pedido a crear.", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDTO.class), examples = @ExampleObject(name = "Ejemplo de crear pedido", summary = "Crea un pedido con los libros seleccionados y el monto pagado.", description = "Solicitud registrar el pago de los libros seleccionados y crear el pedido.", value = """
            {
                "fecha": "",
                "montoTotal": 100.0,
                "descuento": 10.0,
                "nombreCliente": "Juan Perez",
                "items": [
                    {
                        "cantidad": 1,
                        "libroId": 1
                        "monto": 50.0
                    },
                    {
                        "cantidad": 2,
                        "libroId": 2
                        "monto": 50.0
                    }
                ]
            }
            """))))
    @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = PedidoDTO.class), examples = @ExampleObject(name = "Respuesta exitosa",
            summary = "Resultado de pagar un pedido", description = "Resultado de un pedido pagado.", value = """
            {
                    "id": 1,
                    "fecha": "",
                    "estado": "PAGADO",
                    "montoTotal": 100.0,
                    "descuento": 10.0,
                    "nombreCliente": "Juan Perez",
                    "items": [
                            {
                                "id": 1,
                                "cantidad": 1,
                                "libroId": 1
                                "monto": 50.0
                            },
                            {
                                "id": 2,
                                "cantidad": 2,
                                "libroId": 2
                                "monto": 50.0
                            }
                        ]
            }
            """)))
    @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json"), description = "Error en los datos proporcionados para pagar un pedido")
    @PostMapping("/")
    public ResponseEntity<PedidoDTO> crearPedido(@RequestBody @Valid PedidoDTO pedidoDTO) {
        log.debug("REST request to save Pedido : {}", pedidoDTO);

        return ResponseEntity.ok(pedidoService.save(pedidoDTO));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedido(@PathVariable Long id) {
        log.debug("REST request to get Pedido : {}", id);
        return ResponseEntity.ok(pedidoService.getPedidoDTO(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<PedidoDTO>> getAllPedidos() {
        log.debug("REST request to get Pedidos ");
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }
}
