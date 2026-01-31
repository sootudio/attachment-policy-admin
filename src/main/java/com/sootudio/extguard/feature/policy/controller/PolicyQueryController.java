package com.sootudio.extguard.feature.policy.controller;

import com.sootudio.extguard.feature.policy.controller.dto.response.FixedExtensionResponse;
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
    private final FixedExtensionQueryService queryService;

    @GetMapping("/fixed-extensions")
    public ApiResponse<List<FixedExtensionResponse>> getFixedExtensions() {
        return ApiResponse.ok(queryService.findAll());
    }
}
