package com.tim.gestionqualite.repository;


import com.tim.gestionqualite.entity.QualityControl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityControlRepository extends CrudRepository<QualityControl, Long> {

}
