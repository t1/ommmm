package com.github.t1.webresource;

import static javax.persistence.FetchType.*;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;

import lombok.Data;

@Data
@Entity
@WebResource
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Meeting implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @XmlAttribute
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    private Date begin;

    @NotNull
    @ManyToMany(fetch = EAGER)
    private Set<Person> attendees;

    @NotNull
    @ManyToMany(fetch = EAGER)
    private Set<AgendaItem> agendaItem;

    // TODO series
    // TODO duration/end; date-of-writing
    // TODO absentees, distribution

    /** required by JAXB */
    Meeting() {}

    public Meeting(String title, Date begin) {
        this.title = title;
        this.begin = begin;
    }
}
