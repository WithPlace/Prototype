package com.example.withplaceprototype.controller;

import com.example.withplaceprototype.entity.value.Address;
import com.example.withplaceprototype.entity.MatchingRequest;
import com.example.withplaceprototype.utils.ApiResponse;
import com.example.withplaceprototype.utils.MatcingStatus;
import com.example.withplaceprototype.service.impl.RandomMatchingServiceImpl;
import com.example.withplaceprototype.utils.MatchingRequestVO;
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

        MatchingRequest matchingRequest = new MatchingRequest();
        matchingRequest.setMatchingType(matchingRequestBody.getMatchingType());
        matchingRequest.setStatus(MatcingStatus.REQ);

        // TODO
        // 입력받은 address를 kakao api로 검색하여 법정동으로 변환
        matchingRequest.setAddress(new Address("경기도","성남시","신흥동"));

        matchingRequest.setUserId(matchingRequestBody.getUserId()); // TODO-01 이거 없애야 함!!

        return randomMatchingServiceImpl.requestMatching(matchingRequest);
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
