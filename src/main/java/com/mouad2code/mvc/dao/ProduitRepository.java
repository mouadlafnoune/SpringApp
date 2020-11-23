package com.mouad2code.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mouad2code.mvc.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long>{

}
