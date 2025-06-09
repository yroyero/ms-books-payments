package com.unir.payment.repository;

import com.unir.payment.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository  extends JpaRepository<Libro, Long> {

    @Query(value = "SELECT l.* FROM Libro l WHERE " +
           "(:search IS NULL OR l.title LIKE %:search% OR l.author LIKE %:search%" +
            " OR l.genre LIKE %:search%  OR l.isbn = :search" +
            " OR l.summary LIKE %:search%)", nativeQuery = true)
    List<Libro> findByFilters(String search);
}
