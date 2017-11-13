package com.grimmjow8.examples.dealership.restapi.servants;

import com.grimmjow8.examples.dealership.restapi.models.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import static com.grimmjow8.examples.dealership.restapi.servants.ResourcePaths.CAR_PATH;
import static com.grimmjow8.examples.dealership.restapi.servants.ResourcePaths.V1_PATH;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CarImplTest extends BaseImplTest {
    private static final String V1_CAR_PATH = V1_PATH + CAR_PATH;

    /**
     * Test adding car data via POST. The initial request is validated via a subsequent GET on the car ID.
     */
    @Test
    public void testAddingCar() {
        // add car information
        Car expected = new Car("make1", "model1", 1996);
        ResponseEntity<Car> postResponse = this.template.postForEntity(V1_CAR_PATH, expected, Car.class);
        assertEquals(postResponse.getStatusCode(), HttpStatus.OK);

        Car actual = postResponse.getBody();
        assertEquals(actual, expected);

        // retrieve that car information
        ResponseEntity<Car> getResponse = this.template.getForEntity(V1_CAR_PATH + "/" + actual.getId(), Car.class);
        assertEquals(getResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(getResponse.getBody().getId(), actual.getId());
    }

    /**
     * Test removing car data via POST then a subsequent GET all validates the original operation.
     */
    @Test(dependsOnMethods = "testAddingCar")
    public void testDeletingCar() {
        // add car information
        ResponseEntity<Car[]> getResponse = this.template.getForEntity(V1_CAR_PATH , Car[].class);
        assertEquals(getResponse.getStatusCode(), HttpStatus.OK);
        assertTrue(getResponse.getBody().length > 0);

        // delete the car
        Car car = getResponse.getBody()[0];
        this.template.delete(V1_CAR_PATH + "/" + car.getId());

        // verify the deletion
        ResponseEntity<Car[]> entity = this.template.getForEntity(V1_CAR_PATH , Car[].class);
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
        assertEquals(entity.getBody().length, 0);
    }
}
