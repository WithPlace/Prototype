package com.example.withplaceprototype.definition;

public class KakaoConst {

    public enum URL{
        COORD2ADDRESS("coord2address");

        private final String keyName;

        URL(String keyName) {
            this.keyName = keyName;
        }
    }
}
