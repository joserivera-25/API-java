package com.sena.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDTO {
    private Long id;
    private String unidadId;
    private String unidadNombre;
    private String codigo;
    private String nombre;
    private BigDecimal precioUnitario;
}
