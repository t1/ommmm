package com.github.t1.webresource;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class Tags {
    @PersistenceContext
    EntityManager em;

    public Tag of(String key) {
        TypedQuery<Tag> query = em.createNamedQuery("findByKey", Tag.class);
        query.setParameter("key", key);
        return query.getSingleResult();
    }
}
