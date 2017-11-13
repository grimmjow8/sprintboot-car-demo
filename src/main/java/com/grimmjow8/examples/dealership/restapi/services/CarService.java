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

/**
 * Service layer for car data handling. Manages additions, deletions, and lookups of car
 * information. Data stored in memory and will not survice a restart of the application.
 */
@Service
public class CarService {
    private static final Logger LOG = LoggerFactory.getLogger(CarService.class);
    private static final ConcurrentHashMap<String, Car> storage = new ConcurrentHashMap<>();

    /**
     * Add a single car instance to the data store. Checks if the new car details are a duplicate
     * of an existing entry before adding.
     *
     * @param car instance to add to the data store
     * @return car instance with id
     * @throws ApiErrorException on duplicate entry
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
     * Retrieve information on all cars.
     *
     * @return list of cars
     */
    public List<Car> getCars() {
        LOG.info("Retrieving cars, total entries: {}", storage.size());
        return new ArrayList<>(storage.values());
    }

    /**
     * Retrieve information on a single car.
     *
     * @param id lookup id
     * @return car details
     * @throws ApiErrorException if car id does not exist
     */
    @NotNull
    public Car getCar(@NotNull final String id) throws ApiErrorException {
        checkIdExists(id);
        LOG.info("Retrieving car with id: {}", id);
        return storage.get(id);
    }

    /**
     * Delete car information from the data store.
     *
     * @param id lookup id
     */
    public void removeCar(@NotNull String id) throws ApiErrorException {
        checkIdExists(id);
        storage.remove(id);
        LOG.info("Removing car with id: {}, total entries: {}", id, storage.size());
    }

    /**
     * Throws error if car ID does not exist within data store.
     *
     * @param id lookup id
     * @throws ApiErrorException if lookup for id fails
     */
    private void checkIdExists(@NotNull final String id) throws ApiErrorException {
        if (!storage.containsKey(id)) {
            throw ApiErrorException.mkApiErrorException(ApiErrorType.OBJECT_NOT_FOUND, id);
        }
    }

    /**
     * Throws error if given car details already exist within data store.
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
