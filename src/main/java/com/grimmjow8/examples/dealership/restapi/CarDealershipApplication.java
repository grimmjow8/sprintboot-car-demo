package com.grimmjow8.examples.dealership.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class CarDealershipApplication {
    private static final Logger LOG = LoggerFactory.getLogger(CarDealershipApplication.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext cxt = SpringApplication.run(CarDealershipApplication.class, args);
        LOG.info("Starting application: " + CarDealershipApplication.class.getSimpleName());

        String[] beanNames = cxt.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            LOG.info("- bean: " + beanName);
        }
    }
}
