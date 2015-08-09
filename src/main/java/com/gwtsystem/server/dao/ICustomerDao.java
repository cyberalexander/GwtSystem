package com.gwtsystem.server.dao;


import com.gwtsystem.domain.Customer;
import com.gwtsystem.server.dao.exception.DaoException;

import java.util.List;

/**
 * Interface for implementation by CustomerDao class
 * for specific operations with Customer-entity
 *
 * @see com.gwtsystem.server.dao.CustomerDao
 * Created by alexanderleonovich on 02.08.15.
 */
public interface ICustomerDao extends IGenericDao<Customer, Long> {

    /**
     * Implementation of this method will return count
     * of Customers in database
     * @return - count of whole customers in database
     * @throws DaoException - custom Exception class
     * for handle exceptions on DAO layer in application
     */
    Long countCustomers() throws DaoException;

    /**
     * Implementation of this method will return List
     * of Customers Order by Last modified date
     * sort by descending
     * @return - List of Customers
     * @throws DaoException - custom Exception class
     * for handle exceptions on DAO layer in application
     */
    List<Customer> getLastModifiedCustomers() throws DaoException;

    /**
     * Implementation of this method will return List
     * of Customers by input parameters
     * @param metaphones - list of metaphone -keys for search
     * @return list of Customers
     * @throws DaoException - custom Exception class
     * for handle exceptions on DAO layer in application
     */
    List<Customer> getCustomerByMetaPhones(List<String> metaphones) throws DaoException;
}
