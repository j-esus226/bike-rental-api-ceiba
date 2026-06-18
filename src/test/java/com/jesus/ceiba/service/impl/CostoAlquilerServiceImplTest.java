package com.jesus.ceiba.service.impl;

import com.jesus.ceiba.entity.enums.TipoBicicleta;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CostoAlquilerServiceImplTest {

    private final CostoAlquilerServiceImpl service =
            new CostoAlquilerServiceImpl();

    @Test
    void debeCobrarDosHorasCuandoSeUsaUnaHoraDiezMinutos() {

        BigDecimal resultado =
                service.calcularCostoBase(
                        TipoBicicleta.URBANA,
                        70
                );

        assertEquals(
                BigDecimal.valueOf(7000),
                resultado
        );
    }

    @Test
    void noDebeCobrarMultaCuandoNoHayRetraso() {

        BigDecimal multa =
                service.calcularMulta(
                        TipoBicicleta.URBANA,
                        120,
                        2
                );

        assertEquals(
                BigDecimal.ZERO,
                multa
        );
    }

    @Test
    void debeCobrarMultaCuandoHayRetraso() {

        BigDecimal multa =
                service.calcularMulta(
                        TipoBicicleta.URBANA,
                        190,
                        2
                );

        assertEquals(
                BigDecimal.valueOf(3500.0),
                multa
        );
    }

    @Test
    void debeCalcularCostoTotal() {

        BigDecimal total =
                service.calcularCostoTotal(
                        TipoBicicleta.URBANA,
                        190,
                        2
                );

        assertEquals(
                BigDecimal.valueOf(17500.0),
                total
        );
    }

}


