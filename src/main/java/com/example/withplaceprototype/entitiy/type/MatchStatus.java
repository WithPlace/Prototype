package com.example.withplaceprototype.entitiy.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MatchStatus {
    READY("승락 대기중"),
    ING("진행중"),
    END("종료"),
    CANCEL("취소");

    private final String name;
}
