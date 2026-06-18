package com.jesus.ceiba.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearAlquilerRequest {

    @NotBlank
    private String codigoBicicleta;

    @NotBlank
    private String nombreCliente;

    @Min(1)
    private Integer duracionEstimadaHoras;
}