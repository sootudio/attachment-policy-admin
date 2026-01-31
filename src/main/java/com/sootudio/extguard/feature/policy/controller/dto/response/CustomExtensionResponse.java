package com.sootudio.extguard.feature.policy.controller.dto.response;

import lombok.Builder;

@Builder
public record CustomExtensionResponse(
        Long id,
        String extension
) {
    public static CustomExtensionResponse from(Long id, String extension) {
        return CustomExtensionResponse.builder()
                .id(id)
                .extension(extension)
                .build();
    }
}
