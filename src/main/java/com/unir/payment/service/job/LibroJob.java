package com.unir.payment.service.job;

import com.unir.payment.service.LibroService;
import com.unir.payment.service.dto.LibroDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
public class LibroJob {

    private static final Logger log = LoggerFactory.getLogger(LibroJob.class);

    private final LibroService libroService;

    public LibroJob(LibroService libroService) {
        this.libroService = libroService;
    }

    // Run every hour
    @Scheduled(fixedRate = 3600000)
    public void addMissingBooks() {
        log.info("Starting job to add missing books...");
        if (ObjectUtils.isEmpty(libroService.getAll())) {
            // Example: List of books to add
            List<LibroDTO> librosToAdd = List.of(
                    LibroDTO.builder()
                            .title("Don Quijote de la Mancha")
                            .isbn("1234567890")
                            .price(9.99)
                            .summary("Don Quijote de la Mancha es una novela escrita por el autor español Miguel de Cervantes.")
                            .author("Miguel de Cervantes")
                            .genre("Aventura")
                            .releaseDate(LocalDate.of(1605,1,1))
                            .rating(4)
                            .visible(true)
                            .imageUrl("https://aristotelez.com/cdn/shop/files/978841023312.jpg?v=1729039626")
                            .build(),
                    LibroDTO.builder()
                            .title("La odisea")
                            .isbn("4GGGH4567890")
                            .price(19.99)
                            .summary("La Odisea es un poema épico atribuido al poeta griego Homero, que se cree que fue compuesto en el siglo VIII a. C.")
                            .author("Homero")
                            .genre("Aventura")
                            .releaseDate(LocalDate.of(1200,10,1))
                            .rating(4)
                            .visible(true)
                            .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHEkmKAdcC_5HAZ919Qdg0bEfgeHaa1UbDXQ&s")
                            .build(),
                    LibroDTO.builder()
                            .title("El gran Gatsby")
                            .isbn("4GKLH4567HFE0")
                            .price(15.99)
                            .summary("El gran Gatsby es una novela del autor estadounidense F. Scott Fitzgerald. Publicada en 1925, la novela ha sido adaptada a numerosas formas de medios")
                            .author("F. Scott Fitzgerald")
                            .genre("Drama")
                            .releaseDate(LocalDate.of(1925,4,10))
                            .rating(4)
                            .visible(true)
                            .imageUrl("https://www.adazing.com/wp-content/uploads/2012/08/greatgatsby.jpg")
                            .build(),
                    LibroDTO.builder()
                            .title("Moby Dick")
                            .isbn("458279JK68")
                            .price(18.99)
                            .summary("Cuenta la historia del barco ballenero Pequod, a cargo del capitán Ahab, decidido a cobrar venganza contra una legendaria ballena blanca conocida como Moby Dick")
                            .author("Herman Melville")
                            .genre("Novela")
                            .releaseDate(LocalDate.of(1925,4,10))
                            .rating(5)
                            .visible(true)
                            .imageUrl("https://www.exlibric.com/wp-content/uploads/2024/03/Moby-Dick.jpg")
                            .build(),
                    LibroDTO.builder()
                            .title("Romeo y Julieta")
                            .isbn("734984HG87")
                            .price(12.99)
                            .summary("Romeo y Julieta es una tragedia del dramaturgo inglés William Shakespeare. Escrita en torno al año 1595")
                            .author("William Shakespeare")
                            .genre("Tragedia")
                            .releaseDate(LocalDate.of(1595,1,1))
                            .rating(5)
                            .visible(true)
                            .imageUrl("https://images.cdn3.buscalibre.com/fit-in/360x360/ba/69/ba694cd6db3336d0fd36e75377399269.jpg")
                            .build(),
                    LibroDTO.builder()
                            .title("Frankenstein")
                            .isbn("73E49FG87")
                            .price(13.99)
                            .summary("Frankenstein es una novela de terror escrita por la autora británica Mary Shelley. Publicada en 1818")
                            .author("Mary Shelley")
                            .genre("Terror")
                            .releaseDate(LocalDate.of(1818,1,1))
                            .rating(5)
                            .visible(true)
                            .imageUrl("https://www.adazing.com/wp-content/uploads/2012/08/frankenstein.jpg")
                            .build()
            );

            librosToAdd.forEach(libro -> {
                try {
                    libroService.saveLibro(libro);
                    log.info("Added book: {}", libro.getTitle());
                } catch (Exception e) {
                    log.error("Failed to add book: {}", libro.getTitle(), e);
                }
            });
        } else {
            log.info("hay libros en la base de datos, no se añaden nuevos libros");
        }

        log.info("Job completed.");
    }
}