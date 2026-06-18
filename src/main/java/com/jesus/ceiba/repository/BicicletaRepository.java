package com.jesus.ceiba.repository;

import com.jesus.ceiba.entity.Bicicleta;
import com.jesus.ceiba.entity.enums.EstadoBicicleta;
import com.jesus.ceiba.entity.enums.TipoBicicleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BicicletaRepository extends JpaRepository<Bicicleta, Long> {

    Optional<Bicicleta> findByCodigo(String coadigo);

    List<Bicicleta> findByEstado(EstadoBicicleta estado);

    List<Bicicleta> findByEstadoAndTipo(
            EstadoBicicleta estado,
            TipoBicicleta tipo
    );

}