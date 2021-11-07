package com.example.withplaceprototype.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMatchingRequest extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="userMatchingRequestSeq")
    private long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userSeq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matchingReqestSeq")
    private MatchingRequest matchingRequest;


    public UserMatchingRequest(User user,MatchingRequest matchingRequest){
        this.user=user;
        this.matchingRequest=matchingRequest;
    }
}
