package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
