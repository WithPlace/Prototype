package com.example.withplaceprototype.repository;

import com.example.withplaceprototype.definition.RequestStatus;
import com.example.withplaceprototype.entity.MatchRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface MatchRequestRepository extends JpaRepository<MatchRequest, Entity> {
    MatchRequest findByUserIdAndStatus(String userId, RequestStatus status);
}
