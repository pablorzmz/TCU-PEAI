package com.paei.springboot.backend.apirest.model.entity.foo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @EmbeddedId
    private EventID id;

    @OneToMany(mappedBy = "event")
    private List<Meeting> meetings = new ArrayList<>();
}
