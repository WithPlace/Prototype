package com.example.withplaceprototype.controller;

import com.example.withplaceprototype.entity.MatchingRequest;
import com.example.withplaceprototype.utils.ApiResponse;
import com.example.withplaceprototype.utils.MatcingStatus;
import com.example.withplaceprototype.service.impl.RandomMatchingServiceImpl;
import com.example.withplaceprototype.vo.MatchingRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/match/random")
public class RandomMatchingController {
    @Autowired
    private RandomMatchingServiceImpl randomMatchingServiceImpl;

    // 매칭 요청
    @PostMapping("/request")
    public ResponseEntity<ApiResponse<MatchingRequest>> requestMatching(@RequestBody MatchingRequestVO matchingRequestBody) {
        log.info("matchingRequest=[{}]", matchingRequestBody.toString());

        matchingRequestBody.setStatus(MatcingStatus.REQ);
        return randomMatchingServiceImpl.requestMatching(matchingRequestBody);
    }

    // 매칭 수락 (매칭이 이루어진 후 호출 가능)
    public String acceptMatching(){
        return "";
    }

    // 매칭 거절 (매칭이 이루어진 후 호출 가능)
    public String refuseMatching(){
        return "";
    }

    // 매칭 요청 취소 (매칭 대기 중 호출 가능)
    public String cancelMatching(){
        return "";
    }

}
