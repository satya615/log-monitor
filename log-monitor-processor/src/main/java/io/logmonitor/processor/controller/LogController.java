package io.logmonitor.processor.controller;


import io.logmonitor.processor.model.LogEvent;
import io.logmonitor.processor.service.GeminiService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
@CrossOrigin("*")
@Slf4j
public class LogController {

    private final SimpMessagingTemplate messagingTemplate;
    private final GeminiService geminiService;

    public LogController(
            SimpMessagingTemplate messagingTemplate,
            GeminiService geminiService
    ) {
        this.messagingTemplate = messagingTemplate;
        this.geminiService = geminiService;
    }

    @PostMapping
    public void receiveLogs(@RequestBody LogEvent logEvent) {

//        String analysis =
//                geminiService.analyze(logEvent);
//        log.info("Analysed output: {}", analysis);
        logEvent.setAiSuggestion(logEvent.getMessage());

        messagingTemplate.convertAndSend(
                "/topic/logs",
                logEvent
        );
    }
}