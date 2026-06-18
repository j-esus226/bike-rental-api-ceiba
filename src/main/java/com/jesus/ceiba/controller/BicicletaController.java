package com.jesus.ceiba.controller;

import com.jesus.ceiba.entity.Bicicleta;
import com.jesus.ceiba.entity.enums.TipoBicicleta;
import com.jesus.ceiba.service.BicicletaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bicicletas")
public class BicicletaController {

    private final BicicletaService bicicletaService;

    public BicicletaController(
            BicicletaService bicicletaService
    ) {
        this.bicicletaService = bicicletaService;
    }

    @GetMapping("/disponibles")
    public List<Bicicleta> obtenerDisponibles() {

        return bicicletaService
                .obtenerDisponibles();
    }

    @GetMapping("/disponibles/tipo")
    public List<Bicicleta> obtenerDisponiblesPorTipo(
            @RequestParam TipoBicicleta tipo
    ) {

        return bicicletaService
                .obtenerDisponiblesPorTipo(tipo);
    }
}