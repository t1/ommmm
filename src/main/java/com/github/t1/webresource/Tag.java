package com.github.t1.webresource;

import static javax.xml.bind.annotation.XmlAccessType.*;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;

import com.github.t1.webresource.annotations.WebResource;
import com.github.t1.webresource.annotations.WebResourceKey;
import lombok.*;

@Entity
@WebResource
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(NONE)
@NamedQuery(name = "findByKey", query = "SELECT t from Tag t where t.key = :key")
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlTransient
    private @Id
    @GeneratedValue
    Long id;

    @XmlTransient
    private @Column
    @Version
    int version = 0;

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
