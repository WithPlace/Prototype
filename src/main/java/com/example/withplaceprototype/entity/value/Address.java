package com.example.withplaceprototype.entity.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
public class Address {
    @JsonProperty("address_name")
    private String address_name;

    @JsonProperty("region_1depth_name")
    private String region_1depth_name;

    @JsonProperty("region_2depth_name")
    private String region_2depth_name;

    @JsonProperty("region_3depth_name")
    private String region_3depth_name;

    @JsonProperty("x")
    private String x;

    @JsonProperty("y")
    private String y;
}
