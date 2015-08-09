package com.gwtsystem.server.service;

import com.gwtsystem.domain.Customer;
import com.gwtsystem.domain.CustomerType;
import com.gwtsystem.server.dao.ICustomerDao;
import com.gwtsystem.server.dao.ICustomerTypeDao;
import com.gwtsystem.server.dao.exception.DaoException;
import com.gwtsystem.server.service.exception.ServiceException;
import com.gwtsystem.server.util.DtoConverter;
import com.gwtsystem.server.util.MetaPhoneUtil;
import com.gwtsystem.shared.dto.CustomerDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gwtsystem.server.service.exception.ServiceExceptionCode.*;
import static com.gwtsystem.server.util.Constants.Const.*;
import static org.springframework.transaction.annotation.Propagation.*;

/**
 * Service class, for do operations
 * with Customer entity on Service layer
 * In this class entity convert to DTO object
 * Created by alexanderleonovich on 29.07.15.
 */
@Transactional(propagation = REQUIRED, readOnly = false)
@Service("customerService")
public class CustomerService implements ICustomerService {
    private static Logger logger = Logger.getLogger(CustomerService.class);

    @Autowired
    private ICustomerDao customerDao;
    @Autowired
    private ICustomerTypeDao customerTypeDao;
    @Autowired
    private DtoConverter converter;
    @Autowired
    private MetaPhoneUtil metaphoneUtil;

    public CustomerService() {
    }

    /**
     * Method get Customer persisted object from database
     * by unique id and convert it in DTO-object
     * @param id - id of entity in database
     * @return DTO copy of object
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Override
    @Transactional(propagation = SUPPORTS, readOnly = true)
    public CustomerDTO getCustomerDTO(Long id) throws ServiceException {
        Customer customer;
        try {
            customer = customerDao.get(id);
            logger.info(RECEIVED_CUSTOMER + customer);
        } catch (DaoException e) {
            throw new ServiceException(e, IS_SERVICE_01);
        }
        return converter.createCustomerDTO(customer);
    }

    /**
     * This method get all Customer entities from database and
     * convert it in DTO objects
     * @return List of DTO objects
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Override
    @Transactional(propagation = SUPPORTS, readOnly = true)
    public List<CustomerDTO> getAllCustomerDTOs() throws ServiceException {
        List<Customer> customers;
        try {
            customers = customerDao.getAll();
            logger.info(RECEIVE_ALL + customers);
        } catch (DaoException e) {
            throw new ServiceException(e, IS_SERVICE_02);
        }
        return converter.convertToDTOlist(customers);
    }

    /**
     * Method for save new instance of Customer after convertation it from
     * DTO object
     * @param customerDTO object for convertation in Customer entity
     * @return result of operation
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Override
    public Boolean saveCustomer(CustomerDTO customerDTO) throws ServiceException {
        Customer customer = new Customer(customerDTO);
        try {
            CustomerType customerType = customerTypeDao
                    .getByCaption(customerDTO.getCustomerType());
            customer.setCustomerType(customerType);
            customerDao.add(metaphoneUtil.metaphoneEncoding(customer));
            logger.info(PERSIST_CUSTOMER + customer);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, IS_SERVICE_03);
        }
    }

    /**
     * Update instance of Customer? what contains in
     * database after convertation it from DTO object
     * @param customerDTO object for convertation in Customer entity
     * @return result of operation
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Override
    @Transactional(propagation = REQUIRES_NEW, readOnly = false)
    public Boolean updateCustomer(CustomerDTO customerDTO) throws ServiceException {
        Customer customer = new Customer(customerDTO);
        try {
            CustomerType customerType = customerTypeDao.getByCaption(customerDTO.getCustomerType());
            customer.setCustomerType(customerType);
            customerDao.update(metaphoneUtil.metaphoneEncoding(customer));
            logger.info(UPDATING_CUSTOMER + customer);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, IS_SERVICE_04);
        }
    }

    /**
     * Delete persisted entity from database by unique id
     * @param id unique identifier of entity
     * @return result of operation
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Override
    public Boolean deleteCustomer(Long id) throws ServiceException {
        Customer customer;
        try {
            customer = customerDao.get(id);
            customerDao.delete(customer);
            logger.info(DELETE_CUSTOMER + customer);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, IS_SERVICE_05);
        }
    }

    /**
     * Find Customer or Customers by parameters
     * @param searchParams parameters for search
     * @return result of search
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Override
    @Transactional(propagation = SUPPORTS, readOnly = true)
    public List<CustomerDTO> findCustomerDTOs(List<String> searchParams) throws ServiceException {
        List<Customer> customers = null;
        try {
            if (null != searchParams) {
                if (searchParams.size() <= ZERO) {
                    customers = customerDao.getLastModifiedCustomers();
                    logger.info(SEARCH_BY_EMPTY + customers.size());
                } else {
                    List<String> metaphones = metaphoneUtil.convertToMetaphone(searchParams);
                    customers = customerDao.getCustomerByMetaPhones(metaphones);
                    logger.info(SEARCH_BY_METAPHONES + customers.size());
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e, IS_SERVICE_06);
        }
        return converter.convertToDTOlist(customers);
    }

    /**
     * Count Customers in database
     * @return count of Customers
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Override
    public Long countCustomers() throws ServiceException {
        try {
            return customerDao.countCustomers();
        } catch (DaoException e) {
            throw new ServiceException(e, IS_SERVICE_07);
        }
    }
}
