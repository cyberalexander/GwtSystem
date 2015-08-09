package com.gwtsystem.server.dao.exception;

/**
 * My custom class of Exception handling
 * Handle excepptions in DAO - layer
 * Created by alexanderleonovich on 28.07.15.
 */
public class DaoException extends Exception {

    private DaoExceptionCode code;
    private Object[] params;
    private String message;

    public DaoException() {
        super();
    }

    public DaoException(Throwable t, DaoExceptionCode code, Object... params) {
        super(t);
        this.code = code;
        this.params = params;
        this.message = String.format(code.toString(), params);
    }

    public DaoExceptionCode getCode() {
        return code;
    }

    public void setCode(DaoExceptionCode code) {
        this.code = code;
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

    public void setMessage(String message) {
        this.message = message;
    }
}
