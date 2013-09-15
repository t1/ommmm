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
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @XmlAttribute
    @HtmlLinkText
    private String key;

    @NotNull
    @XmlElement
    private String text;

    /** required by JAXB */
    @Deprecated
    Topic() {}

    public Topic(String text) {
        this.text = text;
    }
}
