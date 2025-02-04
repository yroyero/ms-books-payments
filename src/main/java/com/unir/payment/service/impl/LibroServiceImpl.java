package com.unir.payment.service.impl;

import com.unir.payment.service.LibroService;
import com.unir.payment.service.dto.LibroDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


@Component
public class LibroServiceImpl implements LibroService {

    private final Logger log = LoggerFactory.getLogger(LibroServiceImpl.class);

    @Value("${libro.url}")
    private String getLibroUrl;

    private final RestTemplate restTemplate;

    public LibroServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public LibroDTO getLibroDTO(Long libroId) {
        try {
            String url = String.format(getLibroUrl, libroId);
            log.info("Getting book with ID {}. Request to {}", libroId, url);
            return restTemplate.getForObject(url, LibroDTO.class);
        } catch (
                HttpClientErrorException e) {
            log.error("Client Error: {}, Book with ID {}", e.getStatusCode(), libroId);
            return null;
        } catch (
                HttpServerErrorException e) {
            log.error("Server Error: {}, Book with ID {}", e.getStatusCode(), libroId);
            return null;
        } catch (Exception e) {
            log.error("Error: {}, Book with ID {}", e.getMessage(), libroId);
            return null;
        }
    }

}
