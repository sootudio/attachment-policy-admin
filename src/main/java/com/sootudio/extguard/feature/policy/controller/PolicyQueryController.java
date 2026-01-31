package com.sootudio.extguard.feature.policy.controller;

import com.sootudio.extguard.feature.policy.controller.dto.response.CustomExtensionResponse;
import com.sootudio.extguard.feature.policy.controller.dto.response.FixedExtensionResponse;
import com.sootudio.extguard.feature.policy.service.CustomExtensionQueryService;
import com.sootudio.extguard.feature.policy.service.FixedExtensionQueryService;
import com.sootudio.extguard.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/policy")
public class PolicyQueryController {

    private final FixedExtensionQueryService fixedQueryService;
    private final CustomExtensionQueryService customQueryService;

    // 고정 확장자 조회
    @GetMapping("/fixed-extensions")
    public ApiResponse<List<FixedExtensionResponse>> getFixedExtensions() {
        return ApiResponse.ok(fixedQueryService.findAll());
    }

    // 커스텀 확장자 조회
    @GetMapping("/custom-extensions")
    public ApiResponse<List<CustomExtensionResponse>> getCustomExtensions() {
        return ApiResponse.ok(customQueryService.findAll());
    }
}
