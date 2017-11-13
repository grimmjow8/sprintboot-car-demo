package com.grimmjow8.examples.dealership.restapi.exceptions;

import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ApiErrorExceptionTest {
    private static final ApiErrorType notFound = ApiErrorType.OBJECT_NOT_FOUND;

    /**
     * Test default resource bundle loaded with unknown @see java.util.Locale.
     */
    @Test
    public void testMissingResourceFile() {
        String id = "00000";
        ApiErrorException error = ApiErrorException.mkApiErrorException(notFound, Locale.CHINESE, id);
        ResourceBundle bundle = ResourceBundle.getBundle(ApiErrorException.RESOURCE_NAME);
        String expectedMessage = MessageFormat.format(bundle.getString(notFound.name()), id);
        assertEquals(error.getMessage(), expectedMessage);
    }

    /**
     * Test exception creation with locale specified.
     */
    @Test
    public void testExceptionWithLocale() {
        ApiErrorException error = ApiErrorException.mkApiErrorException(notFound, Locale.GERMAN, "xxx");
        assertTrue(error.getMessage().contains("DE"));
    }

    /**
     * Test exception creation without locale specified.
     */
    @Test
    public void testExceptionWithoutLocale() {
        String id = "00000";
        ApiErrorException error = ApiErrorException.mkApiErrorException(notFound, id);
        ResourceBundle bundle = ResourceBundle.getBundle(ApiErrorException.RESOURCE_NAME);
        String expectedMessage = MessageFormat.format(bundle.getString(notFound.name()), id);
        assertEquals(error.getMessage(), expectedMessage);
    }
}
