package com.example.withplaceprototype.entitiy.user;

import com.example.withplaceprototype.entitiy.common.CommonEntity;
import com.example.withplaceprototype.entitiy.match.MatchQueue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;

@Getter
@ToString(callSuper = true)
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    private String id;
    private String pw;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private MatchQueue matchQueue;
}
