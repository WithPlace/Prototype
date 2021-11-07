package com.example.withplaceprototype.utils;

import lombok.Getter;

@Getter
public enum MatcingStatus {
    // 매칭상태 ENUM 타입으로 사용, 정의되지 않은 status는 null이 되므로 주의 필요

    REQ, // 요청 (Request)
    CNL, // 취소 (Cancel) -> 매칭 요청 이후 매칭 완료 이전 가능
    ACP, // 수락 (Accept) -> 매칭 완료된 이후 가능
    REF, // 거부 (Refuse) -> 매칭 완료된 이후 가능
    MSTB, // 만남 대기 (Meet Standby) -> 매칭이 성사 후 만남 대기 상태
    RSTB, // 리뷰 대기 (Meet Standby) -> 만남 이후 리뷰 작성 대기 상태
    END // 완료
}
