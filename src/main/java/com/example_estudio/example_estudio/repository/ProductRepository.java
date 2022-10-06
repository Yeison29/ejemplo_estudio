package com.example_estudio.example_estudio.repository;

import com.example_estudio.example_estudio.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findAllByTypo(String typo);
}
