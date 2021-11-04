package com.example.withplaceprototype.controller;

import com.example.withplaceprototype.definition.RequestStatus;
import com.example.withplaceprototype.definition.ResultCode;
import com.example.withplaceprototype.entity.MatchRequest;
import com.example.withplaceprototype.repository.LawdPlaceRepository;
import com.example.withplaceprototype.repository.MatchRequestRepository;
import com.example.withplaceprototype.entity.MatchResponse;
import com.example.withplaceprototype.service.KakaoApiService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class MatchRequestController {

    private final ObjectMapper mapper;

    private final MatchRequestRepository matchRepo;

    private final LawdPlaceRepository lawdRepo;

    private final KakaoApiService api;

    @PostConstruct
    public void init(){
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @ResponseBody
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/match/request")
    public ResponseEntity<MatchResponse> request(HttpServletRequest httpServletRequest, @RequestBody String body) throws Exception{

        MatchRequest matchRequest = mapper.readValue(body, MatchRequest.class);
        String resultCode = ResultCode.MATCH_CODE_SUCCESS;

        HttpStatus httpStatus;
        log.info("matchRequest : {}", matchRequest.toString());
        //1. validation Check
        if(validationCheck(matchRequest)) {
            httpStatus = HttpStatus.OK;

            String lawdCode = null;
            /* TODO. step1 가져온 데이터 중, 좌표를 주소로 변환 */
            if(matchRequest.getAddressCode() == null){
                String addressName = matchRequest.getAddressName() != null ?
                        matchRequest.getAddressName() : api.transfromCoord2Address(matchRequest).getAddressName();
                 lawdCode = lawdRepo.findByLawdDongEquals(addressName).getLawdCd();
                matchRequest.setAddressCode(lawdCode);
                /* TODO. step2. 주소값을 법정동 code에 매칭하여 저장 */
            } else {
                lawdCode = matchRequest.getAddressCode();
            }

            //4. DB insert
            matchRepo.save(matchRequest);
        } else {
            resultCode = ResultCode.MATCH_CODE_DUPLICATE_REQUEST;
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        //3. Response return
        return new ResponseEntity<>(MatchResponse.builder()
                .userId(matchRequest.getUserId())
                .code(resultCode)
                .build(), httpStatus);
    }

    @ResponseBody
    @RequestMapping(method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/**")
    public ResponseEntity<Object> errorRequestURI(HttpServletRequest httpServletRequest){

        MatchResponse matchResponse = MatchResponse.builder()
                .msg(null)
                .code(ResultCode.MATCH_CODE_UNKNOWN_PATH)
                .build();

        return new ResponseEntity<>(matchResponse, HttpStatus.NOT_FOUND);
    }

    public boolean validationCheck(MatchRequest matchRequest){
        //유저의 중복매칭요청 확인
        if(matchRepo.findByUserIdAndStatus(matchRequest.getUserId(), RequestStatus.REQUESTED) == null){
            return false;
        }

        // 매칭기준 위치정보 확인
        if(matchRequest.getAddressName() == null && (matchRequest.getSiteX() == null || matchRequest.getSiteY() == null)){
            return false;
        }

        return true;
    }
}
