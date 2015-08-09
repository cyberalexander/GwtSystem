package com.gwtsystem.server.dao.exception;

/**
 * Created by alexanderleonovich on 28.07.15.
 */
public enum DaoExceptionCode {

    IS_DAO_01("Can`t get object by id!"),
    IS_DAO_02("Can`t get list of objects!"),
    IS_DAO_03("Can`t add object!"),
    IS_DAO_04("Can`t update object!"),
    IS_DAO_05("Can`t delete object!"),
    IS_DAO_06("Can`t create hql!"),
    IS_DAO_07("Can`t count customers!"),
    IS_DAO_08("Can`t get last modified customers!"),
    IS_DAO_09("Can`t get Customers by metaphone keys"),
    IS_DAO_10("Can`t get CustomerTypes by TypeCaption");


    private final String value;

    DaoExceptionCode(String s) {
        value = s;
    }

    public boolean equalsValue(String value2) {
        return (value2 != null) && value.equals(value2);
    }

    public String toString() {
        return value;
    }
}
