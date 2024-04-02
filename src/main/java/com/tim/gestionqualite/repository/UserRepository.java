package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
