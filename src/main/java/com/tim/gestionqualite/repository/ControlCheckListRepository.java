package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.ControlChecklist;
import com.tim.gestionqualite.entity.UserTeamRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ControlCheckListRepository extends JpaRepository<ControlChecklist, Long> {

    List<ControlChecklist> findByProduitsIdProduit(Long idProduit);

}
