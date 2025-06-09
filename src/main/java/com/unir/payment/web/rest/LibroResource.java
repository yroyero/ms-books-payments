package com.unir.payment.web.rest;

import com.unir.payment.service.LibroService;
import com.unir.payment.service.PedidoService;
import com.unir.payment.service.dto.LibroDTO;
import com.unir.payment.web.rest.response.ResponseDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroResource {

    private final Logger log = LoggerFactory.getLogger(LibroResource.class);

    private final LibroService libroService;
    
    public LibroResource(LibroService libroService) {
        this.libroService = libroService;
    }


    @PostMapping()
    public ResponseEntity<?> createLibroDTO(@Valid @RequestBody LibroDTO book) {
        log.info("Request create book {}", book);
        return ResponseEntity.ok(new ResponseDTO<LibroDTO>(libroService.saveLibro(book)));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getLibroDTOById(@PathVariable Long id) {
        log.info("Request get book by id {}", id);
        LibroDTO book = libroService.findLibroById(id).orElse(null);
        return ObjectUtils.isEmpty(book) ? ResponseEntity.notFound().build() : ResponseEntity.ok(new ResponseDTO<LibroDTO>(book));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateLibroDTO(@PathVariable Long id,@Valid @RequestBody LibroDTO bookDetails) {
        log.info("Request update book {}", bookDetails);
        LibroDTO updatedLibroDTO = libroService.updateLibro(id, bookDetails);
        return ResponseEntity.ok(new ResponseDTO<LibroDTO>(updatedLibroDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibroDTO(@PathVariable Long id) {
        log.info("Request delete book by id {}", id);
        libroService.deleteLibro(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<?> searchLibroDTOs(@RequestParam(required = false) String search   ) {
        log.info("Request search books with params: search={}",search);
        List<LibroDTO> books = libroService.getAllLibros(search);
        return ResponseEntity.ok(new ResponseDTO<List<LibroDTO>>(books));
    }
}
