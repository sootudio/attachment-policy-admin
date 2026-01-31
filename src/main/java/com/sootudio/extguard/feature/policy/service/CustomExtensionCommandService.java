package com.sootudio.extguard.feature.policy.service;

import com.sootudio.extguard.feature.policy.controller.dto.response.CustomExtensionResponse;
import com.sootudio.extguard.feature.policy.entity.CustomExtension;
import com.sootudio.extguard.feature.policy.repository.CustomExtensionRepository;
import com.sootudio.extguard.global.exception.ApiException;
import com.sootudio.extguard.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomExtensionCommandService {

    private static final long MAX_CUSTOM_EXTENSION_COUNT = 200;
    private final CustomExtensionRepository repo;

    public CustomExtensionResponse create(String rawExtension) {
        String extension = normalize(rawExtension);

        // 200개 제한
        if (repo.count() >= MAX_CUSTOM_EXTENSION_COUNT) {
            throw new ApiException(ErrorCode.CUSTOM_EXTENSION_LIMIT_EXCEEDED);
        }

        // 중복체크 1
        if (repo.existsByExtension(extension)) {
            throw new ApiException(ErrorCode.CUSTOM_EXTENSION_DUPLICATED);
        }

        try {
            CustomExtension saved = repo.save(new CustomExtension(extension));
            return CustomExtensionResponse.from(saved.getId(), saved.getExtension());
        } catch (DataIntegrityViolationException e) {
            // DB unique 제약으로 중복체크 2
            throw new ApiException(ErrorCode.CUSTOM_EXTENSION_DUPLICATED);
        }
    }

    // 확장자에 . 있는지 2차 검증
    private String normalize(String raw) {
        String s = raw == null ? "" : raw.trim().toLowerCase();
        if (s.startsWith(".")) s = s.substring(1);
        return s;
    }
}
