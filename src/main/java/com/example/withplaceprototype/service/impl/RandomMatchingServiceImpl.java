package com.example.withplaceprototype.service.impl;

import com.example.withplaceprototype.entity.MatchingRequest;
import com.example.withplaceprototype.entity.User;
import com.example.withplaceprototype.repository.UserRepository;
import com.example.withplaceprototype.utils.ApiResponse;
import com.example.withplaceprototype.utils.MatcingStatus;
import com.example.withplaceprototype.repository.RamdomMatchingRepository;
import com.example.withplaceprototype.service.MatchingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;


@Slf4j
@Service
public class RandomMatchingServiceImpl implements MatchingService {

    @Autowired
    RamdomMatchingRepository matchingRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponse<MatchingRequest>> requestMatching(MatchingRequest matchingRequest) {

        // 입력받은 userId로 User테이블에서 검색하여 저장
        User user = userRepository.findByUserId(matchingRequest.getUserId());
        if(user==null){
            return new ResponseEntity<>(ApiResponse.of(401, "Validation Error: 가입되지 않은 회원입니다.", matchingRequest)
                    ,HttpStatus.valueOf(200));
        }

        if(!verifyUserStatus(matchingRequest.getUserId(), MatcingStatus.REQ)){
            return new ResponseEntity<>(ApiResponse.of(401, "Validation Error: 요청을 처리 할 수 없는 상태입니다.", matchingRequest)
                    ,HttpStatus.valueOf(200));
        }

        try{
            // 매칭 요청 큐에 추가
            matchingRequest.addMatchingRequest(user);
            matchingRepository.save(matchingRequest);
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
