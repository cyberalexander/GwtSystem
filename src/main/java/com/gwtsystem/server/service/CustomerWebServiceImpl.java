package com.gwtsystem.server.service;

import com.gwtsystem.client.service.ICustomerWebService;
import com.gwtsystem.server.service.exception.ServiceException;
import com.gwtsystem.shared.dto.CustomerDTO;
import com.gwtsystem.shared.dto.CustomerTypeDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Server implementation of GWT interface for
 * handle operations in applications and call
 * methods of service layer classes
 * Created by alexanderleonovich on 02.08.15.
 */
@Service("customerWebService")
public class CustomerWebServiceImpl implements ICustomerWebService {
    private static Logger logger = Logger.getLogger(CustomerWebServiceImpl.class);

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerTypeService customerTypeService;


    /**
     * Method call service method,
     * what get CustomerDTO object from database
     * @param id - id of entity in database
     * @return DTO object
     */
    @Override
    public CustomerDTO getCustomerDTObyId(Long id) {
        try {
            return customerService.getCustomerDTO(id);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * Method call Service method for search and getting
     * DTO objects, copies of it contatins in database
     * @param searchParams parameters for search
     * @return result of search operation on service layer
     */
    @Override
    public List<CustomerDTO> findCustomerDTOs(List<String> searchParams) {
        List<CustomerDTO> customerDTOs = null;
        try {
            customerDTOs = customerService.findCustomerDTOs(searchParams);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return customerDTOs;
    }

    /**
     * Method send DTO object in service layer,
     * where it convert to persistence entity and
     * save in database
     * @param customerDTO object add to database
     * @return boolean result of operation
     */
    @Override
    public Boolean addCustomerDTO(CustomerDTO customerDTO) {
        try {
            return customerService.saveCustomer(customerDTO);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return false;
    }

    /**
     * Method send DTO object in service layer,
     * where it convert to persistence entity and
     * update state of entity in database
     * @param customerDTO DTO object for updating state
     * @return boolean result of operation
     */
    @Override
    public Boolean updateCustomerDTO(CustomerDTO customerDTO) {
        try {
            return customerService.updateCustomer(customerDTO);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return false;
    }

    /**
     * Method send unique id of Persisted Customer entity
     * for delete this entity from database
     * @param id id of object for delete from database
     * @return boolean result of operation
     */
    @Override
    public Boolean deleteCustomerDTO(Long id) {
        try {
            return customerService.deleteCustomer(id);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return false;
    }

    /**
     * This method get all DTO objects of Customer entities from database and
     * @return List of DTO Customers
     */
    @Override
    public List<CustomerDTO> getAllCustomerDTOs() {
        try {
            return customerService.getAllCustomerDTOs();
        } catch (ServiceException e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * This method get all DTO objects of CustomerType entities from database and
     * @return List of DTO CustomerTypes
     */
    @Override
    public List<CustomerTypeDTO> getCustomerTypeDTOs() {
        try {
            return customerTypeService.getAllCustomerTypeDTOs();
        } catch (ServiceException e) {
            logger.error(e);
        }
        return null;
    }
}
