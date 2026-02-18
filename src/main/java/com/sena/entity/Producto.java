package com.sena.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unidad_id", length = 3, nullable = false)
    private String unidadId;

    @Column(name="codigo", length = 50, nullable = true)
    private String codigo;

    @Column(name = "nombre", length = 70, nullable = false)
    private String nombre;

    @Column(name="precio_unitario", precision=9,scale = 2, nullable = false)
    private BigDecimal precioUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Unidad unidad;
}
