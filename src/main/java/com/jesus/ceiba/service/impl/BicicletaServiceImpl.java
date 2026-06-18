package com.jesus.ceiba.service.impl;

import com.jesus.ceiba.entity.Bicicleta;
import com.jesus.ceiba.entity.enums.EstadoBicicleta;
import com.jesus.ceiba.entity.enums.TipoBicicleta;
import com.jesus.ceiba.repository.BicicletaRepository;
import com.jesus.ceiba.service.BicicletaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BicicletaServiceImpl implements BicicletaService {

    private final BicicletaRepository bicicletaRepository;

    public BicicletaServiceImpl(
            BicicletaRepository bicicletaRepository
    ) {
        this.bicicletaRepository = bicicletaRepository;
    }

    @Override
    public List<Bicicleta> obtenerDisponibles() {

        return bicicletaRepository.findByEstado(
                EstadoBicicleta.DISPONIBLE
        );
    }

    @Override
    public List<Bicicleta> obtenerDisponiblesPorTipo(
            TipoBicicleta tipo
    ) {

        return bicicletaRepository
                .findByEstadoAndTipo(
                        EstadoBicicleta.DISPONIBLE,
                        tipo
                );
    }
}