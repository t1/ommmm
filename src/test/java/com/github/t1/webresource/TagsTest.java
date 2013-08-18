package com.github.t1.webresource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.*;

import org.junit.*;


public class TagsTest {
    private final Tags tags = new Tags();
    private final Tag tag1 = new Tag("tag1", "description1");

    @Before
    public void before() {
        tags.em = mock(EntityManager.class);
        @SuppressWarnings("unchecked")
        TypedQuery<Tag> query = mock(TypedQuery.class);
        when(tags.em.createNamedQuery("findByKey", Tag.class)).thenReturn(query);
        when(query.setParameter("key", "tag1")).thenReturn(query);
        when(query.getSingleResult()).thenReturn(tag1);
    }

    @Test
    public void shouldCreateTag() throws Exception {
        Tag tag = tags.of("tag1");

        assertEquals(tag1, tag);
    }
}
