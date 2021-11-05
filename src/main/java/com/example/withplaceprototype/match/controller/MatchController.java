package com.example.withplaceprototype.match.controller;

import com.example.withplaceprototype.common.model.CommonResponse;
import com.example.withplaceprototype.match.model.ReqMatchConfirm;
import com.example.withplaceprototype.match.model.ReqRegistRandomMatch;
import com.example.withplaceprototype.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    /**
     * 매치 등록
     */
    @PostMapping("/random")
    public CommonResponse<Boolean> registRandomMatch(@RequestBody ReqRegistRandomMatch request) {
        return CommonResponse.OK(matchService.registRandomMatch(request));
    }


    /**
     * 매치 승락
     */
    @PostMapping("/confirm")
    public CommonResponse<Boolean> matchConfirm(@RequestBody ReqMatchConfirm request) {
        return CommonResponse.OK(matchService.matchConfirm(request));
    }
}
