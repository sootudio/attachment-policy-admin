package com.sootudio.extguard.feature.policy.controller;

import com.sootudio.extguard.feature.policy.controller.dto.request.FixedExtensionUpdateRequest;
import com.sootudio.extguard.feature.policy.service.FixedExtensionCommandService;
import com.sootudio.extguard.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/policy")
public class PolicyCommandController {

    private final FixedExtensionCommandService commandService;

    @PatchMapping("/fixed-extensions/{extension}")
    public ApiResponse<Void> updateFixedExtension(
            @PathVariable String extension,
            @Valid @RequestBody FixedExtensionUpdateRequest request
    ) {
        commandService.updateBlocked(extension, request.blocked());
        return ApiResponse.ok();
    }
}
