package com.sootudio.extguard.feature.policy.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomExtensionCreateRequest(
        @NotBlank(message = "extension is required")
        @Size(max = 20, message = "확장자는 최대 20자까지 입력할 수 있습니다.")
        String extension
) {}
