package com.sootudio.extguard.feature.policy.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomExtensionCreateRequest(
        @NotBlank(message = "extension is required")
        @Size(max = 20, message = "extension length must be <= 20")
        String extension
) {}
