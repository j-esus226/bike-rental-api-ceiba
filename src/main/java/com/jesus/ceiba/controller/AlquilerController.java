package com.jesus.ceiba.controller;

import com.jesus.ceiba.dto.CrearAlquilerRequest;
import com.jesus.ceiba.entity.Alquiler;
import com.jesus.ceiba.service.AlquilerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;

    public AlquilerController(
            AlquilerService alquilerService
    ) {
        this.alquilerService = alquilerService;
    }

    @PostMapping
    public Alquiler iniciarAlquiler(
            @Valid @RequestBody
            CrearAlquilerRequest request
    ) {

        return alquilerService.iniciarAlquiler(
                request.getCodigoBicicleta(),
                request.getNombreCliente(),
                request.getDuracionEstimadaHoras()
        );
    }

    @PostMapping("/{id}/finalizar")
    public Alquiler finalizarAlquiler(
            @PathVariable Long id
    ) {

        return alquilerService
                .finalizarAlquiler(id);
    }

    @GetMapping("/historial/{codigo}")
    public List<Alquiler> obtenerHistorial(
            @PathVariable String codigo
    ) {

        return alquilerService
                .obtenerHistorial(codigo);
    }
}