package com.sootudio.extguard.feature.policy.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record FixedExtensionUpdateRequest(
        @NotNull Boolean blocked
) {}
