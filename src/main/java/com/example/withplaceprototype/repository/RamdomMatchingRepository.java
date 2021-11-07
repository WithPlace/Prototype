package com.example.withplaceprototype.repository;

import com.example.withplaceprototype.entity.MatchingRequest;
import com.example.withplaceprototype.utils.MatcingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RamdomMatchingRepository extends JpaRepository<MatchingRequest,Long> {

    // TODO-01 쿼리 수정 필요
    // @Query("select count(m) from MatchingRequest m join m.userMatchingRequestList l where l.userSeq=:userSeq and m.status=:status")
    @Query("select count(m) from MatchingRequest m where m.userId=:userId and m.status=:status")
    int validationUserStatus(@Param("userId") String userId, @Param("status") MatcingStatus status);


}
