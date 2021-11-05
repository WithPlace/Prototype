package com.example.withplaceprototype.match.schedule;

import com.example.withplaceprototype.match.model.MatchCondition;
import com.example.withplaceprototype.match.service.MatchScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class MatchSchedule {
    private final MatchScheduleService matchScheduleService;

    @Scheduled(cron = "0 */10 * * * *")
    public void makeMatch() throws Exception {
        MatchCondition condition = MatchCondition.builder()
            .matchTotalCnt(5)
            .build();

        log.info("make Match start.");
        matchScheduleService.makeMatch(condition);
        log.info("make Match end.");
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void clearStatus() throws Exception {
        log.info("clear start.");
        matchScheduleService.clearStatus();
        log.info("clear end.");
    }
}
