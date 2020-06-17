package com.github.t1.webresource;

import static org.junit.Assert.*;

import java.io.*;
import java.util.List;

import javax.xml.bind.JAXB;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonTest {
    private final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" //
            + "<person>\n" //
            + "    <first>Joe</first>\n" //
            + "    <last>Doe</last>\n" //
            + "    <tags>\n" //
            + "        <tag key=\"key1\">description1</tag>\n" //
            + "        <tag key=\"key2\">description2</tag>\n" //
            + "    </tags>\n" //
            + "</person>\n";

    private final Tag TAG1 = new Tag("key1", "description1");
    private final Tag TAG2 = new Tag("key2", "description2");

    private final Person person = new Person("Joe", "Doe");

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

        List<Tag> tags = person.getTags();
        assertEquals(2, tags.size());

        Tag tag1 = tags.get(0);
        assertEquals(TAG1.getKey(), tag1.getKey());
        assertEquals(TAG1.getDescription(), tag1.getDescription());

        Tag tag2 = tags.get(1);
        assertEquals(TAG2.getKey(), tag2.getKey());
        assertEquals(TAG2.getDescription(), tag2.getDescription());
    }

    @Test
    public void shouldTagWithTag() throws Exception {
        person.tag(TAG1);

        assertEquals(List.of(TAG1), person.getTags());
    }

    @Test
    public void shouldNotTagTwice() throws Exception {
        person.tag(TAG1);

        person.tag(TAG1);

        assertEquals(List.of(TAG1), person.getTags());
    }

    @Test
    public void shouldUntagWithTag() throws Exception {
        person.tag(TAG1).tag(TAG2);

        boolean untagged = person.untag(TAG1);

        assertTrue(untagged);
        assertEquals(List.of(TAG2), person.getTags());
    }

    @Test
    public void shouldUntagEmpty() throws Exception {
        boolean untagged = person.untag(TAG1);

        assertFalse(untagged);
        assertEquals(List.of(), person.getTags());
    }
}
