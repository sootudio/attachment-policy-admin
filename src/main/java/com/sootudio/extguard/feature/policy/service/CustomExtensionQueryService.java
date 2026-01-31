package com.sootudio.extguard.feature.policy.service;

import com.sootudio.extguard.feature.policy.repository.CustomExtensionRepository;
import com.sootudio.extguard.feature.policy.controller.dto.response.CustomExtensionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomExtensionQueryService {

    private final CustomExtensionRepository repo;

    public List<CustomExtensionResponse> findAll() {
        return repo.findAllByOrderByExtensionAsc().stream()
                .map(e -> CustomExtensionResponse.from(e.getId(), e.getExtension()))
                .toList();
    }
}
