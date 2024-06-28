package com.example.shinydishes.repositories;

import com.example.shinydishes.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
    Users findByEmail(String email);
}
