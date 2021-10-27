package com.example.withplaceprototype.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Getter @Setter @Builder @AllArgsConstructor @JsonIgnoreProperties
public class MatchResponse {
    @JsonProperty
    String userId;

    @JsonProperty
    String code;

    @JsonProperty
    String msg;
}
