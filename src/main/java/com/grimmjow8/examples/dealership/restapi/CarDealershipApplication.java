package com.grimmjow8.examples.dealership.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Car dealership application entry point.
 */
@SpringBootApplication
public class CarDealershipApplication {
    private static final Logger LOG = LoggerFactory.getLogger(CarDealershipApplication.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CarDealershipApplication.class, args);
        LOG.info("Starting application: " + CarDealershipApplication.class.getSimpleName());
    }
}
