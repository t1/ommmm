package com.github.t1.webresource;

import static org.junit.Assert.*;

import java.net.*;
import java.util.Scanner;

import org.junit.Test;

public class PersonIT {
    @Test
    public void shouldLoadOnePerson() throws Exception {
        System.out.println("...." + System.getProperty("cargo.port.offset"));
        URL url = new URL("http://localhost:8180/ommmm/resource/persons/0.xml");
        System.out.println("---> " + url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        System.out.println("---> " + connection.getResponseCode());
        System.out.println("---> " + connection.getContentType());
        try (Scanner in = new Scanner(connection.getInputStream())) {
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
        }
        assertEquals(200, connection.getResponseCode());
    }
}
