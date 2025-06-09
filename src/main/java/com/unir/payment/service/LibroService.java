package com.unir.payment.service;

import com.unir.payment.service.dto.LibroDTO;

import java.util.List;
import java.util.Optional;

public interface LibroService {

    // Other methods can be added here as needed, such as searching for books, etc.
    Optional<LibroDTO> findLibroById(Long libroId);

    LibroDTO saveLibro(LibroDTO libroDTO);

    LibroDTO updateLibro(Long libroId, LibroDTO libroDTO);

    void deleteLibro(Long libroId);

    List<LibroDTO> getAllLibros(String search);

    List<LibroDTO> getAll();
}
