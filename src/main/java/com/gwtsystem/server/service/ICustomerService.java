package com.gwtsystem.server.service;


import com.gwtsystem.server.service.exception.ServiceException;
import com.gwtsystem.shared.dto.CustomerDTO;

import java.util.List;

/**
 * Specific intarface for Customer-service
 * @see com.gwtsystem.server.service.CustomerService
 * Created by alexanderleonovich on 30.07.15.
 */
public interface ICustomerService {

    /**
     * Get Customer persisted object from database
     * by unique id and convert it in DTO-object
     * @param id - id of entity in database
     * @return DTO copy of object
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    CustomerDTO getCustomerDTO(Long id) throws ServiceException;

    /**
     * Get all Customer entities from database and
     * convert it in DTO objects
     * @return List of DTO objects
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    List<CustomerDTO> getAllCustomerDTOs() throws ServiceException;

    /**
     * Save new instance of Customer after convertation it from
     * DTO object
     * @param customerDTO object for convertation in Customer entity
     * @return result of operation
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    Boolean saveCustomer(CustomerDTO customerDTO) throws ServiceException;

    /**
     * Update instance of Customer, what contains in
     * database after convertation it from DTO object
     * @param customerDTO object for convertation in Customer entity
     * @return result of operation
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    Boolean updateCustomer(CustomerDTO customerDTO) throws ServiceException;

    /**
     * Delete persisted entity from database by unique id
     * @param id unique identifier of entity
     * @return result of operation
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    Boolean deleteCustomer(Long id) throws ServiceException;

    /**
     * Find Customer or Customers by parameters
     * @param searchParams parameters for search
     * @return result of search
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    List<CustomerDTO> findCustomerDTOs(List<String> searchParams) throws ServiceException;

    /**
     * Count Customers in database
     * @return count of Customers
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    Long countCustomers() throws ServiceException;

}
