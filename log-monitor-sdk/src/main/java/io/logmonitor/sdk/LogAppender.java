package io.logmonitor.sdk;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class LogAppender extends AppenderBase<ILoggingEvent> {

    private String serverUrl;
    private String applicationName;

    @Override
    protected void append(ILoggingEvent eventObject) {

        String stackTrace = null;

        if (eventObject.getThrowableProxy() != null) {
            stackTrace =
                    eventObject.getThrowableProxy().getMessage();
        }

        LogEvent logEvent = new LogEvent(
                applicationName,
                eventObject.getLevel().toString(),
                eventObject.getLoggerName(),
                eventObject.getFormattedMessage(),
                String.valueOf(eventObject.getTimeStamp()),
                eventObject.getThreadName(),
                stackTrace
        );

        LogClient.send(serverUrl, logEvent);
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}