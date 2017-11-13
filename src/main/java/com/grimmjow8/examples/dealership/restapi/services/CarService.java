package com.grimmjow8.examples.dealership.restapi.services;

import com.grimmjow8.examples.dealership.restapi.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CarService {
    private static final Logger LOG = LoggerFactory.getLogger(CarService.class);
    private static final ConcurrentHashMap<String, Car> storage = new ConcurrentHashMap<>();

    public Car addCar(Car car) {
        String id = UUID.randomUUID().toString();
        car.setId(id);
        LOG.info("Adding car: {}", car.toString());
        this.storage.put(id, car);
        return car;
    }

    public List<Car> getCars() {
        return new ArrayList<>(this.storage.values());
    }

    public Car getCar(String id) {
        LOG.info("Retrieving car with id: {}", id);
        return this.storage.get(id);
    }

    public void removeCar(String id) {
        LOG.info("Removing car with id: {}", id);
        this.storage.remove(id);
    }
}
