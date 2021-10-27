package com.example.withplaceprototype.controller;

import com.example.withplaceprototype.definition.ResultCode;
import com.example.withplaceprototype.entity.MatchRequest;
import com.example.withplaceprototype.entity.MatchResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class MatchRequestController {

    private ObjectMapper mapper = new ObjectMapper();

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
        HttpStatus httpStatus;
        log.info("matchRequest : {}", matchRequest.toString());
        //1. validation Check
        if(validationCheck(matchRequest)) {
            httpStatus = HttpStatus.OK;
        } else{
            //2. DB insert
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        //3. Response return
        return new ResponseEntity<>(MatchResponse.builder()
                .userId(matchRequest.getUserId())
                .code(ResultCode.MATCH_CODE_SUCCESS)
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
        return true;
    }
}
