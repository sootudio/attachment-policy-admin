package com.sootudio.extguard.feature.policy.repository;

import com.sootudio.extguard.feature.policy.entity.FixedExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedExtensionRepository extends JpaRepository<FixedExtension, String> {
}
