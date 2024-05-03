package com.tim.gestionqualite.repository;


import com.tim.gestionqualite.entity.ProduitControlChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitControlChecklistRepository extends JpaRepository<ProduitControlChecklist, Long> {
    ProduitControlChecklist findByControlIdQualityControlAndProduitChecklistIdControlCheckList(Long controlId, Long checklistId);

    List<ProduitControlChecklist> findByControlIdQualityControl(Long controlId);
}
