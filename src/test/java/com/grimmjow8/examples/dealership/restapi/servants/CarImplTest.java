package com.grimmjow8.examples.dealership.restapi.servants;

import com.grimmjow8.examples.dealership.restapi.model.Car;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.Test;


import static com.grimmjow8.examples.dealership.restapi.servants.ResourcePaths.CAR_PATH;
import static com.grimmjow8.examples.dealership.restapi.servants.ResourcePaths.V1_PATH;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class CarImplTest extends BaseImplTest {
    private static final String V1_CAR_PATH = V1_PATH + CAR_PATH;
    
    @Test
    public void testAddingCar() {
        Car expected = new Car("make1", "model1", 1996);
        ResponseEntity<Car> entityPost = this.template.postForEntity(V1_CAR_PATH, expected, Car.class);
        assertEquals(entityPost.getStatusCode(), HttpStatus.OK);
        assertTrue(entityPost.getBody().equals(expected));
        assertEquals(entityPost.getBody(), expected);

        ResponseEntity<Car[]> entity = this.template.getForEntity(V1_CAR_PATH, Car[].class);
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
        Car[] cars = entity.getBody();
        assertTrue(cars.length == 0);


    }
}