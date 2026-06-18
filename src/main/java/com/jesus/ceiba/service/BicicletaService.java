package com.jesus.ceiba.service;

import com.jesus.ceiba.entity.Bicicleta;
import com.jesus.ceiba.entity.enums.TipoBicicleta;

import java.util.List;

public interface BicicletaService {

    List<Bicicleta> obtenerDisponibles();

    List<Bicicleta> obtenerDisponiblesPorTipo(TipoBicicleta tipo);

}