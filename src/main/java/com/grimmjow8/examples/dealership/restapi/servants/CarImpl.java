package com.grimmjow8.examples.dealership.restapi.servants;

import com.grimmjow8.examples.dealership.restapi.exceptions.ApiErrorException;
import com.grimmjow8.examples.dealership.restapi.models.Car;
import com.grimmjow8.examples.dealership.restapi.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static com.grimmjow8.examples.dealership.restapi.servants.ResourcePaths.V1_PATH;
import static com.grimmjow8.examples.dealership.restapi.servants.ResourcePaths.CAR_PATH;

/**
 * Controller layer for car data handling. Defines all car related endpoints.
 * NOTE: No function headers since most of the functionality is within the service layer.
 */
@RestController
public class CarImpl {
    private static final String V1_CAR_PATH = V1_PATH + CAR_PATH;
    private static final String V1_CAR_ID_PATH = V1_CAR_PATH + "/{id}";
    private static final String MEDIA_JSON = "application/json";

    @Autowired
    private CarService service;

    @GetMapping(path = V1_CAR_PATH, produces = MEDIA_JSON)
    public List<Car> getCarsV1() {
        return service.getCars();
    }

    @GetMapping(path = V1_CAR_ID_PATH, produces = MEDIA_JSON)
    public Car getCarV1(@PathVariable("id") String id) throws ApiErrorException {
        return service.getCar(id);
    }

    @PostMapping(path = V1_CAR_PATH, consumes = MEDIA_JSON, produces = MEDIA_JSON)
    public Car setCarV1(@RequestBody Car car) throws ApiErrorException {
        return service.addCar(car);
    }

    @DeleteMapping(path = V1_CAR_ID_PATH, produces = MEDIA_JSON)
    public void removeCarV1(@PathVariable("id") String id) throws ApiErrorException {
        service.removeCar(id);
    }

    @ExceptionHandler({ ApiErrorException.class })
    public ResponseEntity<Object> handleException(ApiErrorException ex, WebRequest request) {
        return new ResponseEntity<>(ex, new HttpHeaders(), ex.getStatus());
    }
}
