package com.grimmjow8.examples.dealership.restapi.servants;

import com.grimmjow8.examples.dealership.restapi.model.Car;
import com.grimmjow8.examples.dealership.restapi.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.grimmjow8.examples.dealership.restapi.servants.ResourcePaths.V1_PATH;
import static com.grimmjow8.examples.dealership.restapi.servants.ResourcePaths.CAR_PATH;

@RestController
public class CarImpl {
    private static final String V1_CAR_PATH = V1_PATH + CAR_PATH;

    @Autowired
    private CarService service;
// TODO resources
// TODO error handling
// TODO documentation
// TODO unit tests
// TODO clenaup
// TODO streamline build process
    @GetMapping(path = V1_CAR_PATH, produces = "application/json")
    public List<Car> getCarsV1() {
        return service.getCars();
    }

    @GetMapping(path = V1_CAR_PATH + "/{id}", produces = "application/json")
    public Car getCarV1(@PathVariable("id") String id) {
        return service.getCar(id);
    }

    @PostMapping(path = V1_CAR_PATH, consumes = "application/json", produces = "application/json")
    public Car setCarV1(@RequestBody Car car) {
        return service.addCar(car);
    }

    @DeleteMapping(path = V1_CAR_PATH + "/{id}", produces = "application/json")
    public void removeCarV1(@PathVariable("id") String id) {
        service.removeCar(id);
    }
}
