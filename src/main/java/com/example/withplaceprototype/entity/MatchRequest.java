package com.example.withplaceprototype.entity;

import com.example.withplaceprototype.definition.RequestStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Table(name="MATCH_REQUEST")
@ToString(exclude = {"seq"})
@Getter @Setter
public class MatchRequest {

    @Id
    @Column(name="seq")
    private int seq;

    @Column(name="userId")
    @JsonProperty
    String userId;

    @Column(name="siteX")
    @JsonProperty
    String siteX;

    @Column(name="siteY")
    @JsonProperty
    String siteY;

    @Column(name="status")
    @JsonProperty
    RequestStatus status;

    @Column(name="requestDate")
    @JsonProperty
    String requestDate;

    @Transient
    @JsonProperty
    String addressName;
}
