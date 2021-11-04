package com.example.withplaceprototype.match;

import com.example.withplaceprototype.config.AppConfig;
import com.example.withplaceprototype.definition.RequestStatus;
import com.example.withplaceprototype.entity.MatchRequest;
import com.example.withplaceprototype.repository.MatchRequestRepository;
import com.example.withplaceprototype.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.PriorityQueue;

@Slf4j
@Component
@RequiredArgsConstructor
public class MatchAgent {

    private AppConfig appConfig;

    private MatchRequestRepository matchRepo;

    @Scheduled(cron = "${match.service.cron-expression}")
    public void match(){

        /* TODO. step1. 특정 기준시간(매칭요청시간 ~ 매칭요청시간+설정시간)의 모든 데이터 전부 가져오기 */
        PriorityQueue<MatchRequest> queue = matchRepo.findByStatusAndRequestDateBefore(RequestStatus.REQUESTED, DateUtil.getNowDateTime(appConfig.getMatchTime()));
        /* TODO. step2. 법정동 code 기준으로 우선순위큐에 저장하여 정렬*/

        int size = queue.size();
        int temp = 0;
        int matchCount = appConfig.getMatchCount();
        String matchHi = "";
        for(int i = 0; i < size; i++){
            if(temp == matchCount)
                temp = 0;

            if(queue.size() > 0 && temp < matchCount){
                matchHi += "[" + queue.poll().toString() + "]";
                temp++;
            }

            if(temp == matchCount){
                log.info("*** matching complete *** : {}", matchHi);
                matchHi = "";
            }
        };
        /* TODO. step3. 매치수만큼 큐에서 꺼내서, match를 만든다. */
    }
}
