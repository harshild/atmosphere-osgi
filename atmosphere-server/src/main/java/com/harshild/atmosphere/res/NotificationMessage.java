package com.harshild.atmosphere.res;

public class NotificationMessage {


    private String eventStatus;
    public void setEventstatus(String eventstatus) {
        this.eventStatus = eventstatus;
    }

    public String getEventstatus() {
        return eventStatus;
    }

    public NotificationMessage(String event, String eventId, String eventStatus) {
        this.eventId = eventId;
        this.event = event;
        this.eventStatus = eventStatus;
    }

    public NotificationMessage() {

    }


    private Long timeStamp;

    public void setTimestamp(Long timestamp) {
        this.timeStamp = timestamp;
    }

    public Long getTimestamp() {
        return timeStamp;
    }


    private String eventId;

    public void setEventid(String eventid) {
        this.eventId = eventid;
    }

    public String getEventid() {
        return eventId;
    }


    private String event;

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

}
