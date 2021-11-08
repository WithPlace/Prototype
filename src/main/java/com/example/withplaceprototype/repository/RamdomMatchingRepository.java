package com.example.withplaceprototype.repository;

import com.example.withplaceprototype.entity.MatchingRequest;
import com.example.withplaceprototype.utils.MatcingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RamdomMatchingRepository extends JpaRepository<MatchingRequest,Long> {

    // @Query("select count(m) from MatchingRequest m where m.userId=:userId and m.status=:status")
    @Query("select count(m) from MatchingRequest m inner join m.userMatchingRequestList l on l.user.userId=:userId and m.status=:status")
    int validationUserStatus(@Param("userId") String userId, @Param("status") MatcingStatus status);


}
