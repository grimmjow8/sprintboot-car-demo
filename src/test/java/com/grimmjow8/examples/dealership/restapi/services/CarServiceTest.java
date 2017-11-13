package com.grimmjow8.examples.dealership.restapi.services;

import com.grimmjow8.examples.dealership.restapi.exceptions.ApiErrorException;
import com.grimmjow8.examples.dealership.restapi.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

@SpringBootTest
public class CarServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    CarService service;

    /**
     * Test lookup with invalid id throws @see com.grimmjow8.examples.dealership.restapi.exceptions.ApiErrorException
     */
    @Test
    public void testGetWithInvalidId() {
        String id = "xxxxxx";
        try {
            service.getCar(id);
            fail("failed to throw api error");
        } catch (ApiErrorException e) {
            assertTrue(e.getMessage().contains(id));
            assertEquals(e.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Test adding duplicate car entry
     *
     * @throws Exception if any unexpected error occurs
     */
    @Test
    public void testAddingDuplicateEntry() throws Exception {
        // initial addition of the entry
        Car car = new Car("make", "model", 2010);
        service.addCar(car);

        // all subsequent additions should fail
        try {
            service.addCar(car);
            fail("failed to throw api error");
        } catch (ApiErrorException e) {
            assertTrue(e.getMessage().contains("Duplicate"));
            assertEquals(e.getStatus(), HttpStatus.BAD_REQUEST);
        }
    }
}
