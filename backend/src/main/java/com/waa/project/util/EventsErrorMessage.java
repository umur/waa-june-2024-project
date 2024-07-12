package com.waa.project.util;

public class EventsErrorMessage {
    private EventsErrorMessage() {}

    public static String eventNotFound(Long eventId) {
        return "Event with id #" + eventId + " not found.";
    }
}
