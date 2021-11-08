package com.example.withplaceprototype.service;

import com.example.withplaceprototype.entity.value.Address;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.Map;

@Slf4j
@Service
public class KakaoMapService {
    private RestTemplate restTemplate=new RestTemplate();

    @Value("${kakao.api-url}")
    private String baseUrl;

    @Value("${kakao.header}")
    private String header;

    @Value("${kakao.api-key}")
    private String apiKey;

    public Address findAddressByXY(String x, String y){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.defaultCharset()));
        headers.set("Authorization",header+" "+apiKey);

        UriComponents builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl+"/v2/local/geo/coord2address.json")
                .queryParam("x",x)
                .queryParam("y",y)
                .build();

        HttpEntity request = new HttpEntity(headers);

        try{
            ResponseEntity<Map> response = restTemplate.exchange(builder.toString(), HttpMethod.GET, request, Map.class);
            log.info("KakaoMapService res"+response.toString());
            if(response.getStatusCode().equals(HttpStatus.OK)){
                JSONObject json =  new JSONObject(response.getBody());
                json = json.getJSONArray("documents").getJSONObject(0);
                json = json.getJSONObject("road_address");
                log.info("KakaoMapService road_address "+json);
                Address address = new Address(
                        json.getString("address_name")
                        , json.getString("region_1depth_name")
                        , json.getString("region_2depth_name")
                        , json.getString("region_3depth_name")
                        , x, y
                );
                log.info("KakaoMapService address"+address.toString());
                return address;
            }
        }catch (Exception e){
            log.error("Address Serch Error : ",e);
            return null;
        }
        return null;
    }

}
