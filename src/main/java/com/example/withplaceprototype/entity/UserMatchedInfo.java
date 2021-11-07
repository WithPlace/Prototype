package com.example.withplaceprototype.entity;

import com.example.withplaceprototype.entity.MatchedInfo;
import com.example.withplaceprototype.entity.MatchingRequest;
import com.example.withplaceprototype.entity.User;

import javax.persistence.*;

@Entity
public class UserMatchedInfo extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="userMatchedInfoSeq")
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userSeq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matchedInfoSeq")
    private MatchedInfo matchedInfo ;
}
