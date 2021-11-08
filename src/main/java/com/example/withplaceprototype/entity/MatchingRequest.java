package com.example.withplaceprototype.entity;

import com.example.withplaceprototype.entity.value.Address;
import com.example.withplaceprototype.utils.MatcingStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter // Setter는 나중에 삭제해야 함.
@NoArgsConstructor // (access = AccessLevel.PROTECTED)
@ToString(exclude = {"userMatchingRequestList"})
public class MatchingRequest extends BaseEntity {
    // 매칭 요청 정보 저장

    @Id @GeneratedValue
    @Column(name="matchingReqestSeq")
    private Long seq;

    private String matchingType; // RANDOM 또는 지인매칭?

    @Enumerated(EnumType.STRING)
    private MatcingStatus status;

    @Embedded
    private Address address;

    private String preference; // 매칭 시 우선순위가 될 선호도 항목

    @OneToMany(mappedBy = "matchingRequest")
    private List<UserMatchingRequest> userMatchingRequestList = new ArrayList<>();

    public UserMatchingRequest addMatchingRequest(User user){
        UserMatchingRequest userMatchingRequest
                = new UserMatchingRequest(user,this);

        return userMatchingRequest;
    }
}
