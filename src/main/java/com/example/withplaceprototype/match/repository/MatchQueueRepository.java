package com.example.withplaceprototype.match.repository;

import com.example.withplaceprototype.entitiy.match.MatchQueue;
import com.example.withplaceprototype.entitiy.type.QueueStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchQueueRepository extends JpaRepository<MatchQueue, Long>, MatchQueueRepositoryCustom {
    List<MatchQueue> findAllByStatusIn(List<QueueStatus> statusList);
}
