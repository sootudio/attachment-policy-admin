package com.sootudio.extguard.feature.policy.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record FixedExtensionUpdateRequest(
        @NotNull(message = "blocked 값이 필요합니다.")
        Boolean blocked
) {}
