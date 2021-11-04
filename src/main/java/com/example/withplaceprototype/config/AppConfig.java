package com.example.withplaceprototype.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@Configuration
public class AppConfig {
    @Value("${lawd.file.path")
    String filePath;

    @Value("${match.service.schedule.match-count")
    int matchCount;

    @Value("${match.service.schedule.match-time")
    String matchTime;
}
