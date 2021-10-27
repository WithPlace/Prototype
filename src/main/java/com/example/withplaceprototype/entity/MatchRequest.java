package com.example.withplaceprototype.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class MatchRequest {
    @JsonProperty
    String userId;

    @JsonProperty
    String siteX;
    @JsonProperty
    String siteY;

    @JsonProperty
    String status;

    @JsonProperty
    String requestDate;
}
