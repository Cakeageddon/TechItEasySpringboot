package com.voorbeeld.TechItEasy.repositories;

import com.voorbeeld.TechItEasy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}