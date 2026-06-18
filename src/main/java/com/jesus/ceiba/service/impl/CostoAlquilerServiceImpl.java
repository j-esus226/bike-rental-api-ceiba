package com.jesus.ceiba.service.impl;

import com.jesus.ceiba.entity.enums.TipoBicicleta;
import com.jesus.ceiba.service.CostoAlquilerService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CostoAlquilerServiceImpl implements CostoAlquilerService {

    @Override
    public BigDecimal calcularCostoBase(
            TipoBicicleta tipo,
            long minutosUso
    ) {

        long horasCobradas = (long) Math.ceil(minutosUso / 60.0);

        BigDecimal tarifa = obtenerTarifaPorHora(tipo);

        return tarifa.multiply(BigDecimal.valueOf(horasCobradas));
    }

    @Override
    public BigDecimal calcularMulta(
            TipoBicicleta tipo,
            long minutosUso,
            Integer duracionEstimadaHoras
    ) {

        long minutosEstimados = duracionEstimadaHoras * 60L;

        if (minutosUso <= minutosEstimados) {
            return BigDecimal.ZERO;
        }

        long minutosRetraso = minutosUso - minutosEstimados;

        long horasRetraso =
                (long) Math.ceil(minutosRetraso / 60.0);

        BigDecimal tarifa = obtenerTarifaPorHora(tipo);

        BigDecimal multaPorHora =
                tarifa.multiply(BigDecimal.valueOf(0.5));

        return multaPorHora.multiply(
                BigDecimal.valueOf(horasRetraso)
        );
    }

    @Override
    public BigDecimal calcularCostoTotal(
            TipoBicicleta tipo,
            long minutosUso,
            Integer duracionEstimadaHoras
    ) {

        BigDecimal costoBase =
                calcularCostoBase(tipo, minutosUso);

        BigDecimal multa =
                calcularMulta(
                        tipo,
                        minutosUso,
                        duracionEstimadaHoras
                );

        return costoBase.add(multa);
    }

    private BigDecimal obtenerTarifaPorHora(
            TipoBicicleta tipo
    ) {

        return switch (tipo) {

            case URBANA ->
                    BigDecimal.valueOf(3500);

            case MONTANA ->
                    BigDecimal.valueOf(5000);

            case ELECTRICA ->
                    BigDecimal.valueOf(7500);
        };
    }
}