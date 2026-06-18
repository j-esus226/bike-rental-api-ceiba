package com.jesus.ceiba.service.impl;

import com.jesus.ceiba.entity.Alquiler;
import com.jesus.ceiba.repository.AlquilerRepository;
import com.jesus.ceiba.repository.BicicletaRepository;
import com.jesus.ceiba.service.AlquilerService;
import com.jesus.ceiba.service.CostoAlquilerService;
import org.springframework.stereotype.Service;
import com.jesus.ceiba.entity.Alquiler;
import com.jesus.ceiba.entity.Bicicleta;
import com.jesus.ceiba.entity.enums.EstadoBicicleta;
import com.jesus.ceiba.exception.BusinessException;
import com.jesus.ceiba.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    private final AlquilerRepository alquilerRepository;

    private final BicicletaRepository bicicletaRepository;

    private final CostoAlquilerService costoAlquilerService;

    public AlquilerServiceImpl(
            AlquilerRepository alquilerRepository,
            BicicletaRepository bicicletaRepository,
            CostoAlquilerService costoAlquilerService
    ) {
        this.alquilerRepository = alquilerRepository;
        this.bicicletaRepository = bicicletaRepository;
        this.costoAlquilerService = costoAlquilerService;
    }

    @Override
    public Alquiler iniciarAlquiler(
            String codigoBicicleta,
            String nombreCliente,
            Integer duracionEstimadaHoras
    ) {

        Bicicleta bicicleta = bicicletaRepository
                .findByCodigo(codigoBicicleta)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe la bicicleta con código "
                                        + codigoBicicleta
                        )
                );

        if (bicicleta.getEstado() != EstadoBicicleta.DISPONIBLE) {

            throw new BusinessException(
                    "La bicicleta no está disponible"
            );
        }

        Alquiler alquiler = Alquiler.builder()
                .nombreCliente(nombreCliente)
                .horaInicio(java.time.LocalDateTime.now())
                .duracionEstimadaHoras(duracionEstimadaHoras)
                .bicicleta(bicicleta)
                .tuvoMulta(false)
                .build();

        bicicleta.setEstado(
                EstadoBicicleta.ALQUILADA
        );

        bicicletaRepository.save(bicicleta);

        return alquilerRepository.save(alquiler);
    }

    @Override
    public Alquiler finalizarAlquiler(Long alquilerId) {

        Alquiler alquiler = alquilerRepository
                .findById(alquilerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe el alquiler con id "
                                        + alquilerId
                        )
                );

        java.time.LocalDateTime horaFin =
                java.time.LocalDateTime.now();

        alquiler.setHoraFin(horaFin);

        long minutosUso =
                java.time.Duration.between(
                        alquiler.getHoraInicio(),
                        horaFin
                ).toMinutes();

        java.math.BigDecimal costoTotal =
                costoAlquilerService.calcularCostoTotal(
                        alquiler.getBicicleta().getTipo(),
                        minutosUso,
                        alquiler.getDuracionEstimadaHoras()
                );

        alquiler.setCostoTotal(costoTotal);

        long minutosEstimados =
                alquiler.getDuracionEstimadaHoras() * 60L;

        alquiler.setTuvoMulta(
                minutosUso > minutosEstimados
        );

        Bicicleta bicicleta =
                alquiler.getBicicleta();

        bicicleta.setEstado(
                EstadoBicicleta.DISPONIBLE
        );

        bicicletaRepository.save(bicicleta);

        return alquilerRepository.save(alquiler);
    }

    @Override
    public List<Alquiler> obtenerHistorial(
            String codigoBicicleta
    ) {

        return alquilerRepository
                .findByBicicletaCodigo(
                        codigoBicicleta
                );
    }
}