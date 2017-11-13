package com.grimmjow8.examples.dealership.restapi.servants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    protected TestRestTemplate template;
}