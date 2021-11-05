package com.example.withplaceprototype.entitiy.match;

import com.example.withplaceprototype.entitiy.common.CommonEntity;
import com.example.withplaceprototype.entitiy.type.MatchStatus;
import com.example.withplaceprototype.match.model.MatchCondition;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Match extends CommonEntity {
    @Id
    @GeneratedValue
    private Long matchSeq;

    private MatchStatus status;

    // 장소 정보 필요 할듯?
    private String storeName;
}
