package com.sootudio.extguard.feature.policy.service;

import com.sootudio.extguard.feature.policy.controller.dto.response.FixedExtensionResponse;
import com.sootudio.extguard.feature.policy.repository.FixedExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FixedExtensionQueryService {

    private final FixedExtensionRepository repo;

    public List<FixedExtensionResponse> findAll() {
        return repo.findAllByOrderByExtensionAsc().stream()
                .map(e -> FixedExtensionResponse.from(e.getExtension(), e.isBlocked()))
                .toList();
    }
}
