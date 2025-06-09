package com.unir.payment.service.impl;

import com.unir.payment.domain.Libro;
import com.unir.payment.repository.LibroRepository;
import com.unir.payment.service.LibroService;
import com.unir.payment.service.dto.LibroDTO;
import com.unir.payment.service.mapper.LibroMapper;
import com.unir.payment.web.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.List;
import java.util.Optional;


@Service
public class LibroServiceImpl implements LibroService {

    private final Logger log = LoggerFactory.getLogger(LibroServiceImpl.class);

    private final LibroRepository libroRepository;

    private final LibroMapper libroMapper;

    public LibroServiceImpl( LibroRepository libroRepository, LibroMapper libroMapper) {

        this.libroRepository = libroRepository;
        this.libroMapper = libroMapper;
    }

    @Override
    public Optional<LibroDTO> findLibroById(Long libroId) {
    log.info("Finding book by ID: {}", libroId);
        return libroRepository.findById(libroId)
                .map(libroMapper::toDto)
                .or(() -> {
                    log.warn("Book with ID {} not found", libroId);
                    return Optional.empty();
                });
    }

    @Override
    public LibroDTO saveLibro(LibroDTO libroDTO) {
    log.info("Saving book: {}", libroDTO);
        if (ObjectUtils.isEmpty(libroDTO)) {
            log.error("Cannot save null book");
            throw new IllegalArgumentException("Cannot save null book");
        }
        Libro libro = libroMapper.toEntity(libroDTO);
        return libroMapper.toDto(libroRepository.save(libro));
    }

    @Override
    public LibroDTO updateLibro(Long libroId, LibroDTO libroDTO) {
    log.info("Updating book with ID {}: {}", libroId, libroDTO);
        if (ObjectUtils.isEmpty(libroDTO)) {
            log.error("Cannot update null book");
            throw new IllegalArgumentException("Cannot update null book");
        }
        return libroRepository.findById(libroId)
                .map(existingLibro -> {
                    Libro updatedLibro = libroMapper.toEntity(libroDTO);
                    updatedLibro.setId(existingLibro.getId());
                    return libroMapper.toDto(libroRepository.save(updatedLibro));
                })
                .orElseThrow(() -> new NotFoundException("Book not found with ID: " + libroId));
    }

    @Override
    public void deleteLibro(Long libroId) {
        log.info("Deleting book with ID: {}", libroId);
        libroRepository.findById(libroId)
                .ifPresentOrElse(
                        libro -> {
                            libroRepository.delete(libro);
                            log.info("Book with ID {} deleted successfully", libroId);
                        },
                        () -> {
                            log.warn("Book with ID {} not found for deletion", libroId);
                            throw new NotFoundException("Book not found with ID: " + libroId);
                        }
                );
    }

    @Override
    public List<LibroDTO> getAllLibros(String search) {
    log.info("Retrieving all books with filters: {}, ",search);
        return libroRepository.findByFilters(search)
                .stream()
                .map(libroMapper::toDto)
                .toList();
    }

    @Override
    public List<LibroDTO> getAll() {
        return libroRepository.findAll()
                .stream()
                .map(libroMapper::toDto)
                .toList();
    }
}
