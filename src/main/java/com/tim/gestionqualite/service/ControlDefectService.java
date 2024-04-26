package com.tim.gestionqualite.service;


import com.tim.gestionqualite.entity.ControlDefect;
import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.Produit;
import com.tim.gestionqualite.repository.ControlDefectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ControlDefectService {

    @Autowired
    ControlDefectRepository controlDefectRepository;

    public List<ControlDefect> getAllDeffect() {
        return  controlDefectRepository.findAll();
    }

    public ControlDefect defectadd(ControlDefect controlDefect) {
        return controlDefectRepository.save(controlDefect);
    }


    public List<ControlDefect> deleteDefect(Long defectId) {
        // Vérifiez d'abord si le produit existe
        if (!controlDefectRepository.existsById(defectId)) {
            throw new IllegalArgumentException("Defect not found");
        }
        // Supprimez le produit de la base de données
        controlDefectRepository.deleteById(defectId);
        // renvoyer la liste des produits
        return controlDefectRepository.findAll();
    }

    public ControlDefect updateDefect(Long idControlDefect, ControlDefect updateDefect) {
        ControlDefect existingDefect = controlDefectRepository.findById(idControlDefect)
                .orElseThrow(() -> new RuntimeException("d'effect non trouvé avec l'ID : " + idControlDefect));

        existingDefect.setCategory(updateDefect.getCategory());
        existingDefect.setCode(updateDefect.getCode());
        existingDefect.setQuantity(updateDefect.getQuantity());
        existingDefect.setDescription(updateDefect.getDescription());

        return controlDefectRepository.save(existingDefect);
    }


}
