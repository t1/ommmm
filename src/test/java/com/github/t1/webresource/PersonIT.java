package com.github.t1.webresource;

import static org.junit.Assert.*;

import java.net.*;
import java.util.Scanner;

import org.junit.*;

@Ignore
public class PersonIT {
    @Test
    public void shouldLoadOnePerson() throws Exception {
        int port = 8080 + Integer.getInteger("cargo.port.offset", 0);
        URL url = new URL("http://localhost:" + port + "/ommmm/resource/persons/0.xml");
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
