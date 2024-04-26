package com.tim.gestionqualite.controller;


import com.tim.gestionqualite.entity.Audit;
import com.tim.gestionqualite.entity.ControlChecklist;
import com.tim.gestionqualite.entity.Produit;
import com.tim.gestionqualite.payloads.ProduitRequest;
import com.tim.gestionqualite.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produit")
public class ProduitController {

    @Autowired
    ProduitService produitService;
    @GetMapping("getAll")
    public ResponseEntity<List<Produit>> getAllProduit() {
        List<Produit> ListProduit = produitService.retrieveAllProduits();
        return ResponseEntity.ok(ListProduit);
    }


    @DeleteMapping("delete/{produitId}")
    public ResponseEntity<List<Produit>> deleteProduit(@PathVariable Long produitId) {
        List<Produit> ListProduit =  produitService.deleteProduit(produitId);
        return ResponseEntity.ok(ListProduit);
    }
    @GetMapping("get/{produitId}")
    public ResponseEntity<Optional<Produit>> getProduitById(@PathVariable Long produitId) {
        Optional<Produit> produit = produitService.retrieveProduitById(produitId);
        return ResponseEntity.ok(produit);
    }
    @PostMapping("/add")
    public ResponseEntity<Produit> addProduitWithoutChecklist(@RequestBody Produit produit) {
        Produit createdProduit = produitService.addProduitWithoutChecklist(produit);
        return ResponseEntity.ok(createdProduit);
    }

    @PostMapping("/assign-checklist-to-produit/{produitId}")
    public ResponseEntity<Produit> addChecklistAndAssignToProduit(@PathVariable Long produitId, @RequestBody ControlChecklist controlChecklist) {
        Produit createdProduit = produitService.addChecklistAndAssignToProduit(produitId, controlChecklist);
        return ResponseEntity.ok(createdProduit);
    }

    @PostMapping("/produit-with-checklists")
    public ResponseEntity<Produit> addProcessAndAssignChecklists(@RequestBody ProduitRequest request) {
        Produit createdProduit = produitService.addProduitWithChecklists(request.getProduit(), request.getChecklistIds());
        return ResponseEntity.ok(createdProduit);
    }
    @PutMapping("/update/{id}")
    public Produit updateProduit(@PathVariable Long id, @RequestBody Produit updatedProduit) {
        return produitService.updateProduit(id, updatedProduit);
    }
}
