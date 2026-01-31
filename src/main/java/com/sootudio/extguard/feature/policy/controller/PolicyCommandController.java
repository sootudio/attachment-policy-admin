package com.sootudio.extguard.feature.policy.controller;

import com.sootudio.extguard.feature.policy.controller.dto.request.CustomExtensionCreateRequest;
import com.sootudio.extguard.feature.policy.controller.dto.request.FixedExtensionUpdateRequest;
import com.sootudio.extguard.feature.policy.controller.dto.response.CustomExtensionResponse;
import com.sootudio.extguard.feature.policy.service.CustomExtensionCommandService;
import com.sootudio.extguard.feature.policy.service.FixedExtensionCommandService;
import com.sootudio.extguard.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/policy")
public class PolicyCommandController {

    private final FixedExtensionCommandService fixedCommandService;
    private final CustomExtensionCommandService customCommandService;

    // 고정 확장자 수정
    @PatchMapping("/fixed-extensions/{extension}")
    public ApiResponse<Void> updateFixedExtension(
            @PathVariable String extension,
            @Valid @RequestBody FixedExtensionUpdateRequest request
    ) {
        fixedCommandService.updateBlocked(extension, request.blocked());
        return ApiResponse.ok();
    }

    // 커스텀 확장자 추가
    @PostMapping("/custom-extensions")
    public ApiResponse<CustomExtensionResponse> createCustomExtension(
            @Valid @RequestBody CustomExtensionCreateRequest request
    ) {
        CustomExtensionResponse created = customCommandService.create(request.extension());
        return ApiResponse.created(created);
    }

    // 커스텀 확장자 삭제
    @DeleteMapping("/custom-extensions/{id}")
    public ApiResponse<Void> deleteCustomExtension(@PathVariable Long id) {
        customCommandService.delete(id);
        return ApiResponse.ok();
    }
}
