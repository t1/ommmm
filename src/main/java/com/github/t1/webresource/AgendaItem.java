package com.github.t1.webresource;

import static javax.xml.bind.annotation.XmlAccessType.*;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

import lombok.*;

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
    private BigDecimal id;

    @NotNull
    @XmlElement
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
