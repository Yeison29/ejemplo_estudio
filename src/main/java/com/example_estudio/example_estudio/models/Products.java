package com.example_estudio.example_estudio.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name= "product")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100,nullable = false)
    private String nombre_product;
    @Column(length = 300)
    private String descripcion;
    @Column(length = 100,nullable = false)
    private String typo;
    @Column(length = 50, nullable = false)
    private int valor;
}
