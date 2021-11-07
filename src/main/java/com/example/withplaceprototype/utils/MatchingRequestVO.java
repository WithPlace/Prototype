package com.example.withplaceprototype.utils;

import lombok.Data;

@Data
public class MatchingRequestVO {
    // Request Body
    private String matchingType;
    private String userId;
    private String address;
    private String status;

    // Response Body

}
