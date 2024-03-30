package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.ControlDefect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlDefectRepository extends JpaRepository<ControlDefect, Long> {

    List<ControlDefect> findByQualityControlIdQualityControl(Long qualityControlId);
}
