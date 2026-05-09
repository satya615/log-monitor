package io.logmonitor.processor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogEvent {

    private String applicationName;
    private String level;
    private String logger;
    private String message;
    private String timestamp;
    private String thread;
    private String stackTrace;
    private String aiSuggestion;
}
