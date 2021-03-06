package com.grimmjow8.examples.dealership.restapi.exceptions;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Error handling for the api. This particular api is user facing. So any strings should
 * be localized. A separate exception mechanism should be added for internal application
 * error handling.
 */
public class ApiErrorException extends Exception {
    public static final String RESOURCE_NAME = "errorStrings";
    private ApiErrorType type;
    private HttpStatus status;

    private ApiErrorException() {
    }

    private ApiErrorException(ApiErrorType type, String message, HttpStatus status) {
        super(message);
        this.type = type;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    /**
     * Creates an ApiError exception that can later be thrown. Based on the error type
     * placeholders within the message will be populated with caller data. The data should
     * provide more information to the user, aiding in diagnosing the problem.
     *
     * @param type error type
     * @param locale user locale
     * @param arguments misc data to include in the error message
     * @return api error exception
     */
    public static ApiErrorException mkApiErrorException(ApiErrorType type, Locale locale, Object... arguments) {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
        String pattern = bundle.getString(type.name());
        String message = "";
        HttpStatus errorStatus = null;
        switch (type) {
            case OBJECT_NOT_FOUND:
                message = MessageFormat.format(pattern, (String)arguments[0]);
                errorStatus = HttpStatus.NOT_FOUND;
                break;
            case DUPLICATE_ENTRY:
                errorStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                break;
        }

        message = (message.isEmpty()) ? (pattern) : (message);
        errorStatus = (Objects.isNull(errorStatus)) ? (HttpStatus.INTERNAL_SERVER_ERROR) : (errorStatus);
        return new ApiErrorException(type, message, errorStatus);
    }

    /**
     * Creates an ApiError exception that ca later be thrown.
     *
     * @param type error type
     * @param arguments misc data to include in the error
     * @return api error exception
     */
    public static ApiErrorException mkApiErrorException(ApiErrorType type, Object... arguments) {
        return mkApiErrorException(type, Locale.ENGLISH, arguments);
    }
}
