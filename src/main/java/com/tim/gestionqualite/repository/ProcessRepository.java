package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {
}
