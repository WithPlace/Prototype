package com.example.withplaceprototype.service;

import com.example.withplaceprototype.entity.MatchingRequest;
import com.example.withplaceprototype.utils.ApiResponse;
import com.example.withplaceprototype.vo.MatchingRequestVO;
import org.springframework.http.ResponseEntity;

public interface MatchingService {

    // 매칭 요청
    public ResponseEntity<ApiResponse<MatchingRequest>> requestMatching(MatchingRequestVO matchingRequest);

    // 매칭 수락 (매칭이 이루어진 후 호출 가능)
    public String acceptMatching();

    // 매칭 거절 (매칭이 이루어진 후 호출 가능)
    public String refuseMatching();

    // 매칭 요청 취소 (매칭 대기 중 호출 가능)
    public String cancelMatching();

}
