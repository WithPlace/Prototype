package com.example.withplaceprototype.match.service;

import com.example.withplaceprototype.entitiy.match.MatchQueue;
import com.example.withplaceprototype.entitiy.type.QueueStatus;
import com.example.withplaceprototype.entitiy.user.User;
import com.example.withplaceprototype.match.model.ReqMatchConfirm;
import com.example.withplaceprototype.match.model.ReqRegistRandomMatch;
import com.example.withplaceprototype.match.repository.MatchQueueRepository;
import com.example.withplaceprototype.match.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class MatchService {
    private final MatchQueueRepository matchQueueRepository;
    private final UserRepository userRepository;

    public boolean registRandomMatch(ReqRegistRandomMatch request) {
        User user = userRepository.findById(request.getId());
        if(ObjectUtils.isEmpty(user))
            return false;

        MatchQueue matchQueue = user.getMatchQueue();
        if(ObjectUtils.isEmpty(matchQueue)) {
            matchQueueRepository.save(
                MatchQueue.builder()
                    .user(user)
                    .status(QueueStatus.WAIT)
                    .matchTotalCnt(request.getMatchCondition().getMatchTotalCnt())
                    .build()
            );
        } else if (QueueStatus.COMMA.equals(matchQueue.getStatus())){
            matchQueue.setStatus(QueueStatus.WAIT);
        } else {
            // 다른 상태에서 막혀있어서 매칭이 등록 안되는 경우가 있을 수 있나?
            return false;
        }

        return true;
    }

    public boolean matchConfirm(ReqMatchConfirm request) {
        User user = userRepository.findById(request.getId());
        if(ObjectUtils.isEmpty(user))
            return false;

        MatchQueue matchQueue = user.getMatchQueue();
        if(ObjectUtils.isEmpty(matchQueue)) {
            return false;
        } else if (QueueStatus.READY.equals(matchQueue.getStatus())){
            matchQueue.setStatus(matchQueue.getStatus());
        } else {
            return false;
        }

        return true;
    }
}
