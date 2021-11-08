package com.example.withplaceprototype.service.impl;

import com.example.withplaceprototype.entity.MatchingRequest;
import com.example.withplaceprototype.entity.User;
import com.example.withplaceprototype.entity.UserMatchingRequest;
import com.example.withplaceprototype.entity.value.Address;
import com.example.withplaceprototype.repository.UserMatchingRequestRepository;
import com.example.withplaceprototype.repository.UserRepository;
import com.example.withplaceprototype.service.KakaoMapService;
import com.example.withplaceprototype.utils.ApiResponse;
import com.example.withplaceprototype.vo.MatchingRequestVO;
import com.example.withplaceprototype.utils.MatcingStatus;
import com.example.withplaceprototype.repository.RamdomMatchingRepository;
import com.example.withplaceprototype.service.MatchingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.function.Predicate;


@Slf4j
@Service
public class RandomMatchingServiceImpl implements MatchingService {

    @Autowired
    RamdomMatchingRepository matchingRepository;
    @Autowired
    UserMatchingRequestRepository userMatchingRequestRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KakaoMapService kakaoMapService;

    @Override
    public ResponseEntity<ApiResponse<MatchingRequest>> requestMatching(MatchingRequestVO matchingRequestBody) {

        // 입력받은 userId로 User테이블에서 검색하여 저장
        User user = userRepository.findByUserId(matchingRequestBody.getUserId());
        if(user==null){
            return new ResponseEntity<>(ApiResponse.of(401, "Validation Error: 가입되지 않은 회원입니다.", null)
                    ,HttpStatus.valueOf(200));
        }

        if(!verifyUserStatus(matchingRequestBody.getUserId(), matchingRequestBody.getStatus())){
            return new ResponseEntity<>(ApiResponse.of(401, "Validation Error: 요청을 처리 할 수 없는 상태입니다.", null)
                    ,HttpStatus.valueOf(200));
        }

        MatchingRequest matchingRequest = new MatchingRequest();
        matchingRequest.setMatchingType(matchingRequestBody.getMatchingType());
        matchingRequest.setStatus(matchingRequestBody.getStatus());

        // 입력받은 x, y를 kakao api로 검색하여 체크 후 저장
        Address address = kakaoMapService.findAddressByXY(matchingRequestBody.getX(),matchingRequestBody.getY());
        if(address==null){
            return new ResponseEntity<>(ApiResponse.of(401, "Validation Error: 주소 검색 실패.", null)
                    ,HttpStatus.valueOf(200));
        }
        matchingRequest.setAddress(address);

        try{
            // 매칭 요청 큐에 추가
            UserMatchingRequest userMatchingRequest = matchingRequest.addMatchingRequest(user);
            matchingRepository.save(matchingRequest);
            userMatchingRequestRepository.save(userMatchingRequest);
        }catch(Exception e){
            log.error("Request Insert Error {}",e);
            return new ResponseEntity<>(ApiResponse.of(501, "System Error: 요청 처리 중 에러 발생", matchingRequest)
                    ,HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(ApiResponse.of(200, "Success: 요청이 정상 처리되었습니다.", matchingRequest)
                ,HttpStatus.valueOf(200));
    }

    @Override
    public String acceptMatching() {
        return null;
    }

    @Override
    public String refuseMatching() {
        return null;
    }

    @Override
    public String cancelMatching() {
        return null;
    }

    private boolean verifyUserStatus(String userId, MatcingStatus reqType){
        // 현재 유저가 각 요청을 할 수 있는 상태인지 체크
        // userId를 기반으로 현재 할당된 matching의 상태 확인
        Predicate<Integer> equals0 = (c) -> c.equals(0);
        Predicate<Integer> equals1 = (c) -> c.equals(1);

        switch (reqType){
            case REQ:
                return equals0.test(matchingRepository.validationUserStatus(userId,MatcingStatus.REQ));
            case CNL:
                return equals1.test(matchingRepository.validationUserStatus(userId,MatcingStatus.CNL));
            case ACP:
                return equals1.test(matchingRepository.validationUserStatus(userId,MatcingStatus.ACP));
            case REF:
                return equals1.test(matchingRepository.validationUserStatus(userId,MatcingStatus.REF));
            default: ; return false;
        }

    }
}
