package com.github.t1.webresource;

import javax.inject.Inject;
import javax.persistence.*;

import com.github.t1.log.Logged;
import com.github.t1.webresource.codec.*;
import com.github.t1.webresource.meta.Item;

@Logged
public class PageHeader implements HtmlDecorator {
    @Inject
    HtmlOut out;

    @PersistenceContext
    private EntityManager em;

    public String foo() {
        return em.find(Person.class, 2L).toString();
    }

    @Override
    public void decorate(Item item) {
        out.write(foo());
    }
}
