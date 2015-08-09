package com.gwtsystem.server.dao;


import com.gwtsystem.domain.CustomerType;
import com.gwtsystem.server.dao.exception.DaoException;

/**
 * Interface for implementation by CustomerTypeDao class
 * for specific operations with CustomerType-entity
 *
 * @see com.gwtsystem.server.dao.CustomerTypeDao
 * Created by alexanderleonovich on 02.08.15.
 */
public interface ICustomerTypeDao extends IGenericDao<CustomerType, Long> {

    /**
     * Get CustomerType from database by unique caption
     *
     * @param customerTypeCaption - CustomerType Caprtion
     * @return CustomerType object
     * @throws DaoException - custom Exception class
     *                      for handle exceptions on DAO layer in application
     */
    CustomerType getByCaption(String customerTypeCaption) throws DaoException;
}
