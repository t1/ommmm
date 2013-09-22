package com.github.t1.webresource;

import static javax.xml.bind.annotation.XmlAccessType.*;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

import lombok.*;

import com.github.t1.webresource.codec.HtmlLinkText;

@Entity
@WebResource
@Getter
@Setter
@ToString
@XmlRootElement
@XmlAccessorType(NONE)
public class AgendaItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @XmlAttribute
    private long id;

    @NotNull
    @XmlElement
    @HtmlLinkText
    private String text;

    @ManyToOne
    @XmlElement
    private Topic topic;

    // TODO meeting
    // TODO responsible persons (RACI?)

    /** required by JAXB */
    @Deprecated
    AgendaItem() {}

    public AgendaItem(String text, Topic topic) {
        this.text = text;
        this.topic = topic;
    }
}
