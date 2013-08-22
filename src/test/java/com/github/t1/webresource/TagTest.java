package com.github.t1.webresource;

import static org.junit.Assert.*;

import java.io.*;

import javax.xml.bind.JAXB;

import org.junit.Test;

public class TagTest {
    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

    private static final String XML = XML_HEADER + "\n" //
            + "<tag key=\"X\">hiho</tag>\n";
    private static final Tag TAG = new Tag("X", "hiho");

    @Test
    public void shouldMarshal() throws Exception {
        StringWriter writer = new StringWriter();
        JAXB.marshal(TAG, writer);

        assertEquals(XML, writer.toString());
    }

    @Test
    public void shouldUnmarshal() throws Exception {
        Tag tag = JAXB.unmarshal(new StringReader(XML), Tag.class);

        assertEquals(TAG.getKey(), tag.getKey());
        assertEquals(TAG.getDescription(), tag.getDescription());
    }

}
