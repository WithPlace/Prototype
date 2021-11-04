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
@Table(name="LAWDPLACE")
@ToString(exclude = {"lawdId"})
@Getter @Setter
public class LawdPlace {

    @Id
    @Column(name="lawd_id")
    private int lawdId;

    @Column(name="lawd_cd")
    @JsonProperty
    String lawdCd;

    @Column(name="lawd_dong")
    @JsonProperty
    String lawdDong;

    @Column(name="exist")
    @JsonProperty
    String exist;

    @Column(name="reg_date")
    @JsonProperty
    String regDate;

    @Column(name="upt_date")
    @JsonProperty
    String uptDate;
}
