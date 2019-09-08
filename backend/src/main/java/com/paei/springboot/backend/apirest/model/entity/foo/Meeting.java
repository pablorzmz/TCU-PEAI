package com.paei.springboot.backend.apirest.model.entity.foo;

import javax.persistence.*;

@Entity
public class Meeting {

    @EmbeddedId
    private MeetingID id;

    @MapsId("eventId")
    @JoinColumns({
            @JoinColumn(name="EventID", referencedColumnName="EventID"),
            @JoinColumn(name="SourceID", referencedColumnName="SourceID")
    })
    @ManyToOne
    private Event event;
}
