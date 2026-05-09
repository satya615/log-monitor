package io.logmonitor.sdk;

public class LogEvent {

    private String applicationName;
    private String level;
    private String logger;
    private String message;
    private String timestamp;
    private String thread;
    private String stackTrace;

    public LogEvent() {
    }

    public LogEvent(
            String applicationName,
            String level,
            String logger,
            String message,
            String timestamp,
            String thread,
            String stackTrace
    ) {
        this.applicationName = applicationName;
        this.level = level;
        this.logger = logger;
        this.message = message;
        this.timestamp = timestamp;
        this.thread = thread;
        this.stackTrace = stackTrace;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}