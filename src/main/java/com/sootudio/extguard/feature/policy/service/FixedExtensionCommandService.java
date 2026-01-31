package com.sootudio.extguard.feature.policy.service;

import com.sootudio.extguard.feature.policy.entity.FixedExtension;
import com.sootudio.extguard.feature.policy.repository.FixedExtensionRepository;
import com.sootudio.extguard.global.exception.ApiException;
import com.sootudio.extguard.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FixedExtensionCommandService {

    private final FixedExtensionRepository repo;

    public void updateBlocked(String rawExtension, boolean blocked) {
        String extension = normalize(rawExtension);

        FixedExtension fixed = repo.findById(extension)
                .orElseThrow(() -> new ApiException(ErrorCode.FIXED_EXTENSION_NOT_FOUND));

        fixed.changeBlocked(blocked);
    }

    private String normalize(String raw) {
        if (raw == null) return "";
        String s = raw.trim().toLowerCase();
        if (s.startsWith(".")) s = s.substring(1);
        return s;
    }
}
