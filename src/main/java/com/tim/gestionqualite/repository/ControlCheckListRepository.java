package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.ControlCheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlCheckListRepository extends JpaRepository<ControlCheckList, Long> {

    List<ControlCheckList> findByQualityControlIdQualityControl(Long qualityControlId);



}
