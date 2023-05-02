package com.figo.weatherapp.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Murtozayev Manguberdi
 * 01.05.2023
 */
@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledComponent {


    @Scheduled(fixedRate = 3_600_000L)
    private void reloadLessonCountAndLessonDurationFromAcademicContentService() {
        log.info("Scheduled task reloadLessonCountAndLessonDurationFromAcademicContentService in process");
    }
}
