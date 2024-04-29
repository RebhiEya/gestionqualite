package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.Role;
import com.tim.gestionqualite.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleName name);
}
