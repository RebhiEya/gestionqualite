package com.tim.gestionqualite.repository;


import com.tim.gestionqualite.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    Optional<Produit> findBycategory(String category);
    List<Produit> findAll();
}
