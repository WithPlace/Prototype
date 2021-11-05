package com.example.withplaceprototype.match.repository;

import com.example.withplaceprototype.entitiy.match.MatchQueue;
import com.example.withplaceprototype.match.model.MatchCondition;
import java.util.List;

public interface MatchQueueRepositoryCustom {
    public List<MatchQueue> makeMatch(MatchCondition condition);
}
