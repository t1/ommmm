package com.github.t1.webresource;

import java.util.Date;

import javax.xml.bind.JAXB;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;

public class MeetingTest {

    private final Meeting meeting = new Meeting();
    private static long nextId;

    private Person snape(String first, String last) {
        Person person = new Person();
        person.setId(nextId++);
        person.setFirst(first);
        person.setLast(last);
        return person;
    }

    private AgendaItem agendaItem(String text) {
        return new AgendaItem(text, null);
    }

    @Test
    public void shouldMarshal() throws Exception {
        meeting.setId(123L);
        meeting.setTitle("Potions And Poisons");
        meeting.setBegin(new Date());
        meeting.setAttendees(ImmutableSet.of(snape("Severus", "Snape"), snape("Harry", "Potter")));
        meeting.setAgendaItem(ImmutableSet.of(agendaItem("potions"), agendaItem("poisons")));

        JAXB.marshal(meeting, System.out);

        // no assert? yeah... we only test, if it marshals at all
    }
}
