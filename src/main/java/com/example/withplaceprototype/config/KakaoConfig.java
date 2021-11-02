package com.example.withplaceprototype.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Getter @Setter
@Configuration
public class KakaoConfig {

    @Value("${kakao.api.url")
    private HashMap<String,String> url;

    @Value("{kakao.api.serviceKey")
    private String serviceKey; //설정에서는 암호화해서 들어온다

    public void decrpytServiceKey(){
        //추후에 암호화를 해야하는 대상
    }
}
