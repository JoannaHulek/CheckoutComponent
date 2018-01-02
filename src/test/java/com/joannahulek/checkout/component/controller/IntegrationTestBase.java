package com.joannahulek.checkout.component.controller;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTestBase {
    @LocalServerPort
    private int port;

    String base;

    @Autowired
    TestRestTemplate template;

    @Before
    public void setUp() {
        this.base = "http://localhost:" + port + "/";
    }
}