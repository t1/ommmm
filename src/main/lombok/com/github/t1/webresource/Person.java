package com.github.t1.webresource;

import static com.google.common.base.Preconditions.*;
import static javax.xml.bind.annotation.XmlAccessType.*;

import java.io.Serializable;
import java.util.*;

import javax.inject.Inject;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;

import lombok.*;

import com.github.t1.webresource.codec.HtmlLinkText;
import com.google.common.collect.ImmutableList;

@Entity
@WebResource
@Getter
@Setter
@ToString
@XmlRootElement
@XmlAccessorType(NONE)
@HtmlLinkText("${first} ${last}")
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

    @XmlTransient
    private @OneToMany(fetch = FetchType.EAGER)
    Set<Tag> tags = new HashSet<>();

    /** required by JAXB */
    Person() {}

    public Person(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @XmlList
    @XmlElement(name = "tags")
    public List<String> getTagList() {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        for (Tag tag : tags) {
            builder.add(tag.getKey());
        }
        return builder.build();
    }

    public void setTagList(List<String> tags) {
        for (String tag : tags) {
            tag(tag);
        }
    }

    @Inject
    transient Tags tagFactory;

    public Person tag(Tag tag) {
        checkNotNull(tag);
        tags.add(tag);
        return this;
    }

    public Person tag(String string) {
        tag(tagFactory.of(string));
        return this;
    }

    public boolean untag(Tag tag) {
        if (tags == null)
            return false;
        return tags.remove(tag);
    }

    public boolean untag(String string) {
        return untag(tagFactory.of(string));
    }
}
