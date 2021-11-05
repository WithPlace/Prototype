package com.example.withplaceprototype.match.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqRegistRandomMatch {
    private String id;

    // 위치 정보

    // 원하는 매칭 정보
    private MatchCondition matchCondition;
}
