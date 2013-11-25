package com.github.t1.webresource;

import static javax.xml.bind.annotation.XmlAccessType.*;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;

import lombok.*;

import com.github.t1.webresource.codec.HtmlTitle;

@Entity
@WebResource
@Getter
@Setter
@ToString
@XmlRootElement
@XmlAccessorType(NONE)
@HtmlTitle("${first} ${last}")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @XmlTransient
    private Long id;

    @XmlElement
    @NotNull
    @Size(min = 1, max = 100)
    @Pattern(regexp = "\\p{Alpha}*", message = "must contain only alphabetical characters")
    private @Column
    String first;

    @XmlElement
    @NotNull
    @Size(min = 1, max = 50)
    @Pattern(regexp = "\\p{Alpha}*", message = "must contain only alphabetical characters")
    private @Column
    String last;

    @XmlElement(name = "tag")
    @XmlElementWrapper(name = "tags")
    private @OneToMany(fetch = FetchType.EAGER)
    List<Tag> tags = new ArrayList<>();

    /** required by JAXB */
    Person() {}

    public Person(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public Person tag(Tag tag) {
        if (!tags.contains(tag))
            tags.add(tag);
        return this;
    }

    public boolean untag(Tag tag) {
        return tags.remove(tag);
    }
}
