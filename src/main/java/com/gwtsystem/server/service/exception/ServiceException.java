package com.gwtsystem.server.service.exception;

/**
 * My custom class of Exception handling
 * Handle excepptions in SERVICE - layer
 * Created by alexanderleonovich on 28.07.15.
 */
public class ServiceException extends Exception{

    private ServiceExceptionCode code;
    private Object[] params;
    private String message;

    public ServiceException(Throwable t, ServiceExceptionCode code, Object... params) {
        super(t);
        this.code = code;
        this.params = params;
        this.message = String.format(code.toString(), params);
    }

    public ServiceExceptionCode getCode() {
        return code;
    }

    public void setCode(ServiceExceptionCode code) {
        this.code =code;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message){
    this.message = message;
    }
}
