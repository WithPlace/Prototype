package com.example.withplaceprototype.service;

import com.example.withplaceprototype.config.KakaoConfig;
import com.example.withplaceprototype.definition.KakaoConst;
import com.example.withplaceprototype.entity.MatchRequest;
import com.example.withplaceprototype.vo.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoApiService {

    private final RestTemplate restTemplate;

    private final KakaoConfig kakaoConfig;

    private ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void init(){
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public Address transfromCoord2Address(MatchRequest match) throws URISyntaxException {
        String x = match.getSiteX();
        String y = match.getSiteY();
        Map<String, String> body = new HashMap<String, String>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.defaultCharset()));
        String url = kakaoConfig.getUrl().get(KakaoConst.URL.COORD2ADDRESS.name());
        ResponseEntity<Address> response = restTemplate.getForEntity(new URI(url),Address.class);
        //TODO. 테스트 코드 작성
        return response.getBody();
    }
}
