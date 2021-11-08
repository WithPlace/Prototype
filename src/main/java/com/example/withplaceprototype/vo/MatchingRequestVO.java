package com.example.withplaceprototype.vo;

import com.example.withplaceprototype.utils.MatcingStatus;
import lombok.Data;

@Data
public class MatchingRequestVO {
    // Request Body
    private String matchingType;
    private String userId;
    private MatcingStatus status;
    private String x;
    private String y;

    // Response Body

}
