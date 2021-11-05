package com.example.withplaceprototype.entitiy.type;

import javax.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QueueStatus {
    WAIT("매치 대기중"),
    READY("수락 대기"),
    COMPLETE("매치 완료"),
    END("매칭 종료"),
    CANCEL("매치 취소"),
    COMMA("코마");

    private final String name;

}
