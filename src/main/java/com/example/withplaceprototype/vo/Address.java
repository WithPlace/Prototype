package com.example.withplaceprototype.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Getter @Setter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class Address {
    @JsonProperty
    String address_name;

    @JsonProperty
    String x;

    @JsonProperty
    String y;

    @JsonProperty
    String region_1depth_name;

    @JsonProperty
    String region_2depth_name;

    @JsonProperty
    String region_3depth_name;

    @JsonProperty
    String addressCode;
}
