package com.jesus.ceiba.service;

import com.jesus.ceiba.entity.Alquiler;

import java.util.List;

public interface AlquilerService {

    Alquiler iniciarAlquiler(
            String codigoBicicleta,
            String nombreCliente,
            Integer duracionEstimadaHoras
    );

    Alquiler finalizarAlquiler(Long alquilerId);

    List<Alquiler> obtenerHistorial(String codigoBicicleta);

}