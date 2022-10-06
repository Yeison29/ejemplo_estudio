package com.example_estudio.example_estudio.controllers;

import com.example_estudio.example_estudio.models.Products;
import com.example_estudio.example_estudio.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(value = "/product")
    public ResponseEntity createProducto(@RequestBody Products products){
        try {
            productRepository.save(products);
            return new ResponseEntity(products, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping(value = "/product/{id}")
    public ResponseEntity getProduct(@PathVariable Long id){
        Optional<Products> product = productRepository.findById(id);
        if(product.isPresent()){
            return new ResponseEntity(product,HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping(value = "/products")
    public ResponseEntity listProducts(){
        List<Products> products = productRepository.findAll();
        if(products.isEmpty()){
            return  ResponseEntity.notFound().build();
        }else{
            return new ResponseEntity(products,HttpStatus.OK);
        }
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity editProduct(@PathVariable Long id, @RequestBody Products products){
        Optional<Products> productBD = productRepository.findById(id);
        if(productBD.isPresent()){
            try {
                productBD.get().setNombre_product(products.getNombre_product());
                productBD.get().setDescripcion(products.getDescripcion());
                productBD.get().setTypo(products.getTypo());
                productBD.get().setValor(products.getValor());
                productRepository.save(productBD.get());
                return new ResponseEntity(productBD, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        Optional<Products> productDB = productRepository.findById(id);
        if(productDB.isPresent()){
            productRepository.delete(productDB.get());
            return new ResponseEntity(productDB,HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
