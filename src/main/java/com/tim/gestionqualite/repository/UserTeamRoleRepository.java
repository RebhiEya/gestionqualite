package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.Team;
import com.tim.gestionqualite.entity.User;
import com.tim.gestionqualite.entity.UserTeamRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTeamRoleRepository extends JpaRepository<UserTeamRole, Long> {
    Optional<UserTeamRole> findByUserAndTeam(User user, Team team);
}
