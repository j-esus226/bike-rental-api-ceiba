package com.jesus.ceiba.repository;

import com.jesus.ceiba.entity.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    List<Alquiler> findByBicicletaCodigo(String codigo);

}