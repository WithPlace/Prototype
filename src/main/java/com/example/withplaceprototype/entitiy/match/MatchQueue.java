package com.example.withplaceprototype.entitiy.match;

import com.example.withplaceprototype.entitiy.common.CommonEntity;
import com.example.withplaceprototype.entitiy.type.QueueStatus;
import com.example.withplaceprototype.entitiy.user.User;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
public class MatchQueue extends CommonEntity {

    @Id
    @GeneratedValue
    private Long matchQueueSeq;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userSeq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matchSeq")
    private Match match;

    @Audited
    @Enumerated(EnumType.STRING)
    private QueueStatus status;

    private int matchTotalCnt;

    public void setStatus(QueueStatus status) {
        this.status = status;
    }
}
