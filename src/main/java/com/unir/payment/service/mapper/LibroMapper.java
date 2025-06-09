package com.unir.payment.service.mapper;

import com.unir.payment.domain.Libro;
import com.unir.payment.service.dto.LibroDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibroMapper extends EntityMapper<LibroDTO, Libro> {
}
