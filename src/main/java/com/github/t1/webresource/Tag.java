package com.github.t1.webresource;

import static lombok.AccessLevel.*;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;

import lombok.*;

/** Normally, entities are not very good candidates to be value types (equals/hashCode have to take
 * the id and version into account, which doesn't make sense for value types), but Tags, actually, are!
 * They are identified by their key and value, everything else is not in the xml and as immutable as it
 * goes with entities. */
@Entity
@Data
@Setter(PRIVATE)
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@WebResource
@NamedQuery(name = "findByKey", query = "SELECT t from Tag t where t.key = :key")
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter(PACKAGE)
    @Setter(PACKAGE)
    private @Id
    @GeneratedValue
    Long id;

    @Getter(PACKAGE)
    @Setter(PACKAGE)
    private @Column
    @Version
    int version = 0;

    @Setter(PACKAGE)
    @XmlAttribute
    private @NotNull
    @Size(min = 1, max = 20)
    @Pattern(regexp = "\\p{Alpha}*", message = "the key of a tag must contain only alphabetical characters")
    @WebResourceKey
    String key;

    @XmlValue
    private String description;

    /** @deprecated required by JAXB */
    @Deprecated
    Tag() {}

    public Tag(String name, String description) {
        this.key = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Tag [" + key + ((description == null) ? "" : "; " + description) + "]";
    }
}
