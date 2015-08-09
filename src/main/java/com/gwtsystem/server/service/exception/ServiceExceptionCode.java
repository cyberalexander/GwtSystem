package com.gwtsystem.server.service.exception;

/**
 * Enum of messages with exception-codes for Service-layer
 * @author Alexander Leonovich
 * Created 30.07.15.
 * @version 1.0
 */
public enum ServiceExceptionCode {

    // TODO REFACTOR CONTENT OF STRINGS
    IS_SERVICE_01("Can`t get Customer"),
    IS_SERVICE_02("Can`t get all Customers"),
    IS_SERVICE_03("Can`t save Customer"),
    IS_SERVICE_04("Can`t update Customer"),
    IS_SERVICE_05("Can`t delete Customer by id"),
    IS_SERVICE_06("Can`t find customers. Error search!"),
    IS_SERVICE_07("Can`t count Customers!"),
    IS_SERVICE_08("Can`t get CustomerType by ID!"),
    IS_SERVICE_09("Can`t get CustomerType by caption!"),
    IS_SERVICE_10("Can`t get all CustomerTypes!"),
    IS_SERVICE_11("Can`t save CustomerType!"),
    IS_SERVICE_12("Can`t update CustomerType!"),
    IS_SERVICE_13("Can`t delete CustomerType!");

    private final String value;

    private ServiceExceptionCode(String s) {
        value = s;
    }

    public boolean equalsValue(String value2) {
        return (value2 != null) && value.equals(value2);
    }

    public String toString() {
        return value;
    }
}
