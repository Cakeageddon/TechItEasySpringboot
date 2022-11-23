package com.voorbeeld.TechItEasy.repositories;

import com.voorbeeld.TechItEasy.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelevisionRepository extends JpaRepository<Television, Long> {
    Optional <Television> findByBrand(String brand);
}
