package com.sootudio.extguard.feature.policy.repository;

import com.sootudio.extguard.feature.policy.entity.CustomExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomExtensionRepository extends JpaRepository<CustomExtension, Long> {
    boolean existsByExtension(String extension);
    Optional<CustomExtension> findByExtension(String extension);
}
