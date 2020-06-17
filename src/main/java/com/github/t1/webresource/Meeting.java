package com.github.t1.webresource;

import static javax.persistence.FetchType.*;
import static javax.xml.bind.annotation.XmlAccessType.*;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;

import com.github.t1.webresource.annotations.WebResource;
import com.github.t1.webresource.annotations.WebSubResource;
import lombok.Data;

@Data
@Entity
@WebResource
@XmlRootElement
@XmlAccessorType(FIELD)
public class Meeting implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private @XmlTransient
    Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    private Date begin;

    @NotNull
    @ManyToMany(fetch = EAGER)
    @OrderBy("last, first")
    private @WebSubResource
    Set<Person> attendees;

    @NotNull
    @ManyToMany(fetch = EAGER)
    @OrderBy("title")
    private @WebSubResource
    @XmlElementWrapper(name = "agenda")
    @XmlElement(name = "item")
    Set<AgendaItem> agendaItem;

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
