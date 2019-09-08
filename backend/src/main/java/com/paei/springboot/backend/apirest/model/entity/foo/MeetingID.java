package com.paei.springboot.backend.apirest.model.entity.foo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MeetingID implements Serializable {

    @Column(name = "MeetingID")
    private Long meetingId;

    private EventID eventId;

    //implements equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingID meetingID = (MeetingID) o;
        return Objects.equals(meetingId, meetingID.meetingId) &&
                Objects.equals(eventId, meetingID.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingId, eventId);
    }
}