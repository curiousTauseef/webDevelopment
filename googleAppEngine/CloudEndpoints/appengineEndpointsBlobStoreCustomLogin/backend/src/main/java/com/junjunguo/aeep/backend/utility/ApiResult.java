package com.junjunguo.aeep.backend.utility;

/**
 * This file is part of appengineEndpointsBlobStoreCustomLogin
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on March 28, 2016.
 */
public class ApiResult {
    private String statusCode;
    private String message;

    /**
     * Instantiates a new Api result.
     */
    public ApiResult() {
    }

    /**
     * Instantiates a new Api result.
     * @param statusCode the status code
     * @param message    the message
     */
    public ApiResult(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * Sets status code.
     * @param statusCode the status code
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Sets message.
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets status code.
     * @return the status code
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Gets message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
