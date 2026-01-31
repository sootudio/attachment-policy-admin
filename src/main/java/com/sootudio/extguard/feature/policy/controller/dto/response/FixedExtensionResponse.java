package com.sootudio.extguard.feature.policy.controller.dto.response;

import lombok.Builder;

@Builder
public record FixedExtensionResponse(
        String extension,
        boolean blocked
) {
    public static FixedExtensionResponse from(String extension, boolean blocked) {
        return FixedExtensionResponse.builder()
                .extension(extension)
                .blocked(blocked)
                .build();
    }
}