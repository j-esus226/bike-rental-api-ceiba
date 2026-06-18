package com.jesus.ceiba.service;

import com.jesus.ceiba.entity.enums.TipoBicicleta;

import java.math.BigDecimal;

public interface CostoAlquilerService {

    BigDecimal calcularCostoBase(
            TipoBicicleta tipo,
            long minutosUso
    );

    BigDecimal calcularMulta(
            TipoBicicleta tipo,
            long minutosUso,
            Integer duracionEstimadaHoras
    );

    BigDecimal calcularCostoTotal(
            TipoBicicleta tipo,
            long minutosUso,
            Integer duracionEstimadaHoras
    );

}