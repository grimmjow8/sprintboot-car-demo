package com.grimmjow8.examples.dealership.restapi.models;

import java.util.Objects;

/**
 * Car model.
 */
public class Car {
    private String id;
    private String make;
    private String model;
    private int year;

    public Car() {
    }

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    @Override
    public int hashCode() {
        return Objects.hash(make, model, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Car car = (Car) obj;
        return Objects.equals(make, car.getMake())
                && Objects.equals(model, car.getModel())
                && Objects.equals(year, car.getYear());
    }

    @Override
    public String toString() {
        return "make: " + make + ", models: " + model + ", year: " + Integer.toString(year);
    }
}
