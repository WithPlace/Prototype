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

    @JsonProperty("address_name")
    String addressName;

    @JsonProperty("x")
    String x;

    @JsonProperty("y")
    String y;

    @JsonProperty("region_1depth_name")
    String dept1;

    @JsonProperty("region_2depth_name")
    String dept2;

    @JsonProperty("region_3depth_name")
    String dept3;
}
