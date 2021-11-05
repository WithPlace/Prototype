package com.example.withplaceprototype.match.service;

import com.example.withplaceprototype.entitiy.match.Match;
import com.example.withplaceprototype.entitiy.match.MatchHistory;
import com.example.withplaceprototype.entitiy.match.MatchQueue;
import com.example.withplaceprototype.entitiy.type.MatchStatus;
import com.example.withplaceprototype.entitiy.type.QueueStatus;
import com.example.withplaceprototype.match.model.MatchCondition;
import com.example.withplaceprototype.match.repository.MatchHistoryRepository;
import com.example.withplaceprototype.match.repository.MatchQueueRepository;
import com.example.withplaceprototype.match.repository.MatchRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchScheduleService {

    private final MatchQueueRepository matchQueueRepository;
    private final MatchRepository matchRepository;
    private final MatchHistoryRepository matchHistoryRepository;

    public void makeMatch(MatchCondition condition) {
        List<MatchQueue> matchQueueList = matchQueueRepository.makeMatch(condition);
        if (matchQueueList.size() != condition.getMatchTotalCnt()) {
            log.info("not enough waiting count");
            return;
        }

        Match match = Match.builder()
            .status(MatchStatus.READY)
            .build();
        matchRepository.save(match);
        for (MatchQueue matchQueue : matchQueueList) {
            matchHistoryRepository.save(
                MatchHistory.builder()
                    .user(matchQueue.getUser())
                    .match(match)
                    .build()
            );

            matchQueue.setStatus(QueueStatus.READY);
        }

        // READY 상태를 읽고 앞단에 수락여부 UI 제공할 수 있도록
    }

    public void clearStatus() {
        List<MatchQueue> clearTarget = matchQueueRepository.findAllByStatusIn(List.of(QueueStatus.CANCEL, QueueStatus.END));
        for (MatchQueue matchQueue : clearTarget) {
            log.info("id : {}, status : {}, updateAt : {}", matchQueue.getUser().getUserSeq(), matchQueue.getStatus(), matchQueue.getUpdatedAt());
            // 10분 후 매치 가능 상태
            // Match조회하여 평가완료 인지 확인 활 수도 있겟다
            if (matchQueue.getUpdatedAt().plusMinutes(10).isAfter(LocalDateTime.now())) {
                matchQueue.setStatus(QueueStatus.COMMA);
            }
        }
    }
}
