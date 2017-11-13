package com.grimmjow8.examples.dealership.restapi.services;

import com.grimmjow8.examples.dealership.restapi.exceptions.ApiErrorException;
import com.grimmjow8.examples.dealership.restapi.exceptions.ApiErrorType;
import com.grimmjow8.examples.dealership.restapi.models.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CarService {
    private static final Logger LOG = LoggerFactory.getLogger(CarService.class);
    private static final ConcurrentHashMap<String, Car> storage = new ConcurrentHashMap<>();

    /**
     * Add car to data store.
     *
     * @param car instance to add to the data store
     * @return car instance with id
     */
    @NotNull
    public Car addCar(@NotNull final Car car) throws ApiErrorException {
        checkDuplicateEntry(car);
        final String id = UUID.randomUUID().toString();
        car.setId(id);
        storage.put(id, car);
        LOG.info("Adding car {}, total entries: {}", car.toString(), storage.size());
        return car;
    }

    /**
     * Get all cars.
     *
     * @return list of cars
     */
    public List<Car> getCars() {
        LOG.info("Retrieving cars, total entries: {}", storage.size());
        return new ArrayList<>(storage.values());
    }

    /**
     * Get a single car.
     *
     * @param id lookup id
     * @return car instance
     */
    @NotNull
    public Car getCar(@NotNull final String id) throws ApiErrorException {
        checkId(id);
        LOG.info("Retrieving car with id: {}", id);
        return storage.get(id);
    }

    /**
     * Delete car from data store.
     *
     * @param id lookup id
     */
    public void removeCar(@NotNull String id) throws ApiErrorException {
        checkId(id);
        storage.remove(id);
        LOG.info("Removing car with id: {}, total entries: {}", id, storage.size());
    }

    /**
     * Check if ID exists within data store, throw error if lookup fails.
     *
     * @param id lookup id
     * @throws ApiErrorException if lookup for id fails
     */
    private void checkId(@NotNull final String id) throws ApiErrorException {
        if (!storage.containsKey(id)) {
            throw ApiErrorException.mkApiErrorException(ApiErrorType.OBJECT_NOT_FOUND, id);
        }
    }

    /**
     * Check if car already exists within data store.
     *
     * @param car lookup item
     * @throws ApiErrorException if duplicate entry detected
     */
    private void checkDuplicateEntry(@NotNull final Car car) throws ApiErrorException {
        if (storage.values().stream().filter(item -> item.equals(car)).findAny().isPresent()) {
            throw ApiErrorException.mkApiErrorException(ApiErrorType.DUPLICATE_ENTRY);
        }
    }
}
