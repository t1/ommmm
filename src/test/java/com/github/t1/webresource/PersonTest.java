package com.github.t1.webresource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;

import javax.xml.bind.JAXB;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.ImmutableSet;

@RunWith(MockitoJUnitRunner.class)
public class PersonTest {
    private final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" //
            + "<person>\n" //
            + "    <first>Joe</first>\n" //
            + "    <last>Doe</last>\n" //
            + "    <tags>key1 key2</tags>\n" //
            + "</person>\n";

    private final Tag TAG1 = new Tag("key1", "description1");
    private final Tag TAG2 = new Tag("key2", "description2");

    private final Person person = new Person("Joe", "Doe");

    @Mock
    Tags tagFactory;

    @Before
    public void before() {
        person.tagFactory = tagFactory;
        when(tagFactory.of(TAG1.getKey())).thenReturn(TAG1);
        when(tagFactory.of(TAG2.getKey())).thenReturn(TAG2);
    }

    @Test
    public void shouldMarshal() throws Exception {
        person.tag(TAG1).tag(TAG2);

        StringWriter xml = new StringWriter();
        JAXB.marshal(person, xml);

        assertEquals(XML, xml.toString());
    }

    @Test
    public void shouldUnmarshal() throws Exception {
        Person person = JAXB.unmarshal(new StringReader(XML), Person.class);

        assertEquals(person.getFirst(), person.getFirst());
        assertEquals(person.getLast(), person.getLast());
        // TODO fix assertEquals(ImmutableSet.of(TAG1, TAG2), person.getTags());
    }

    @Test
    public void shouldTagWithTag() throws Exception {
        person.tag(TAG1);

        assertEquals(ImmutableSet.of(TAG1), person.getTags());
    }

    @Test
    public void shouldTagWithString() throws Exception {
        person.tag(TAG1.getKey());

        assertEquals(ImmutableSet.of(TAG1), person.getTags());
    }

    @Test
    public void shouldNotTagTwice() throws Exception {
        person.tag(TAG1);

        person.tag(TAG1);

        assertEquals(ImmutableSet.of(TAG1), person.getTags());
    }

    @Test
    public void shouldUntagWithTag() throws Exception {
        person.tag(TAG1).tag(TAG2);

        boolean untagged = person.untag(TAG1);

        assertTrue(untagged);
        assertEquals(ImmutableSet.of(TAG2), person.getTags());
    }

    @Test
    public void shouldUntagWithString() throws Exception {
        person.tag(TAG1).tag(TAG2);

        boolean untagged = person.untag(TAG1.getKey());

        assertTrue(untagged);
        assertEquals(ImmutableSet.of(TAG2), person.getTags());
    }

    @Test
    public void shouldUntagEmpty() throws Exception {
        boolean untagged = person.untag(TAG1);

        assertFalse(untagged);
        assertEquals(ImmutableSet.of(), person.getTags());
    }
}
