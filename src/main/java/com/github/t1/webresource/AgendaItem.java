package com.github.t1.webresource;

import static javax.xml.bind.annotation.XmlAccessType.*;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

import com.github.t1.webresource.annotations.WebResource;
import lombok.*;

import com.github.t1.webresource.codec.HtmlTitle;

@Entity
@WebResource
@Getter
@Setter
@ToString
@XmlRootElement
@XmlAccessorType(NONE)
public class AgendaItem implements Serializable, Comparable<AgendaItem> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @XmlTransient
    private Long id;

    @NotNull
    @XmlElement
    @HtmlTitle
    private String title;

    @ManyToOne
    @XmlElement
    @OrderBy("key")
    private Topic topic;

    // TODO meeting
    // TODO responsible persons (RACI?)

    /** required by JAXB */
    @Deprecated
    AgendaItem() {}

    public AgendaItem(String title, Topic topic) {
        this.title = title;
        this.topic = topic;
    }

    @Override
    public int compareTo(AgendaItem that) {
        return this.title.compareTo(that.title);
    }
}
