package com.example.withplaceprototype.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MatchedInfo extends BaseEntity{
    // 매칭 완료된 정보 저장

    @Id
    @GeneratedValue
    @Column(name="matchedInfoSeq")
    private Long seq;

    // TODO-02 구현 필요
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="placeSeq")
    private Place place;

    @OneToMany(mappedBy = "matchedInfo")
    private List<UserMatchedInfo> userMatchedInfoList = new ArrayList<>();

}
