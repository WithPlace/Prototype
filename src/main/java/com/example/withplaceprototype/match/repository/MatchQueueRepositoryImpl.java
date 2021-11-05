package com.example.withplaceprototype.match.repository;

import static com.example.withplaceprototype.entitiy.match.QMatchQueue.matchQueue;

import com.example.withplaceprototype.entitiy.match.MatchQueue;
import com.example.withplaceprototype.entitiy.type.QueueStatus;
import com.example.withplaceprototype.match.model.MatchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;

public class MatchQueueRepositoryImpl implements MatchQueueRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public MatchQueueRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<MatchQueue> makeMatch(MatchCondition condition) {
        return queryFactory
            .selectFrom(matchQueue)
            .where(matchQueue.status.eq(QueueStatus.WAIT),
                // 매칭 별 세부 조건
                eqMatchTotalCnt(condition.getMatchTotalCnt()))
            .limit(condition.getMatchTotalCnt())
            .orderBy(matchQueue.updatedAt.asc())
            .fetch();
    }

    private BooleanExpression eqMatchTotalCnt(Integer matchTotalCnt) {
        return matchTotalCnt == null ? null : matchQueue.matchTotalCnt.eq(matchTotalCnt);
    }
}
