package com.crudActivity.camila_acosta_mobileFix.repository;

import com.crudActivity.camila_acosta_mobileFix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // The Optional attribute is used to represent a value that can be empty instead of returning null
    Optional<User> findByUsername(String username);

}
