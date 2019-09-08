package com.paei.springboot.backend.apirest.model.entity.foo;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EventID implements Serializable {

    @Column(name = "EventID")
    private Long eventId;

    @Column(name = "SourceID")
    private Long sourceId;

    //implements equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventID eventID = (EventID) o;
        return Objects.equals(eventId, eventID.eventId) &&
                Objects.equals(sourceId, eventID.sourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, sourceId);
    }
}