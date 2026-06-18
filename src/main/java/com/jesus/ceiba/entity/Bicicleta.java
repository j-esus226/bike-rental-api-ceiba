package com.jesus.ceiba.entity;

import com.jesus.ceiba.entity.enums.EstadoBicicleta;
import com.jesus.ceiba.entity.enums.TipoBicicleta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bicicletas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bicicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoBicicleta tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoBicicleta estado;

}