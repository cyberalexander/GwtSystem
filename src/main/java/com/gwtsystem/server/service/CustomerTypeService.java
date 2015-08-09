package com.gwtsystem.server.service;

import com.gwtsystem.domain.CustomerType;
import com.gwtsystem.server.dao.ICustomerTypeDao;
import com.gwtsystem.server.dao.exception.DaoException;
import com.gwtsystem.server.service.exception.ServiceException;
import com.gwtsystem.server.service.exception.ServiceExceptionCode;
import com.gwtsystem.server.util.DtoConverter;
import com.gwtsystem.shared.dto.CustomerTypeDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gwtsystem.server.util.Constants.Const.*;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

/**
 * Service class, for do operations
 * with CustomerType entity on Service layer
 * In this class entity convert to DTO object
 * Created by alexanderleonovich on 29.07.15.
 */
@Service("customerTypeService")
@Transactional(propagation = REQUIRED, readOnly = false)
public class CustomerTypeService implements ICustomerTypeService {
    private static Logger logger = Logger.getLogger(CustomerTypeService.class);

    @Autowired
    private ICustomerTypeDao customerTypeDao;
    @Autowired
    private DtoConverter converter;

    public CustomerTypeService() {

    }

    /**
     * Method for get CustomerType persisted object from database
     * by unique id and convert it in DTO-object
     * @param id - id of entity in database
     * @return DTO copy of object
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Override
    @Transactional(propagation = SUPPORTS, readOnly = true)
    public CustomerTypeDTO getCustomerTypeDTO(Long id) throws ServiceException {
        CustomerType customerType;
        try {
            customerType = customerTypeDao.get(id);
            logger.info(GET_CUSTOMER_TYPE_1 + customerType);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceExceptionCode.IS_SERVICE_08);
        }
        return converter.createCustomerTypeDTO(customerType);
    }

    /**
     * Method for get  CustomerType persisted object from database
     * by unique caption String and convert it in DTO-object
     * @param customerTypeCaption - caption of entity in database
     * @return DTO copy of object
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Override
    public CustomerTypeDTO getCustomerTypeDTObyCaption(String customerTypeCaption)
            throws ServiceException {
        CustomerType customerType;
        try {
            customerType = customerTypeDao.getByCaption(customerTypeCaption);
            logger.info(GET_CUSTOMER_TYPE_2 + customerType);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceExceptionCode.IS_SERVICE_09);
        }
        return converter.createCustomerTypeDTO(customerType);
    }

    /**
     * Method for get  all CustomerTypes entities from database and
     * convert it in DTO objects
     * @return List of DTO objects
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Override
    @Transactional(propagation = SUPPORTS, readOnly = true)
    public List<CustomerTypeDTO> getAllCustomerTypeDTOs() throws ServiceException {
        List<CustomerType> customerTypes;
        try {
            customerTypes = customerTypeDao.getAll();
            logger.info(GET_CUSTOMER_TYPES + customerTypes.size());
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceExceptionCode.IS_SERVICE_10);
        }
        return converter.convertToTypeDTOlist(customerTypes);
    }

    /**
     * Method save new instance of CustomerType after convertation it from
     * DTO object
     * @param customerTypeDTO object for convertation in CustomerType entity
     * @return result of operation
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Override
    public Boolean saveCustomerType(CustomerTypeDTO customerTypeDTO)
            throws ServiceException {
        CustomerType customerType = new CustomerType(customerTypeDTO);
        try {
            customerTypeDao.add(customerType);
            logger.info(PERSIST_CUSTOMER_TYPE + customerType);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceExceptionCode.IS_SERVICE_11);
        }
    }

    /**
     * Methode update persisted instance of CustomerType, what contains in
     * database after convertation it from DTO object
     * @param customerTypeDTO object for convertation in customerType entity
     * @return result of operation
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Override
    public Boolean updateCustomerType(CustomerTypeDTO customerTypeDTO)
            throws ServiceException {
        CustomerType customerType = new CustomerType(customerTypeDTO);
        try {
            customerTypeDao.update(customerType);
            logger.info(UPDATE_CUSTOMER_TYPE + customerType);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceExceptionCode.IS_SERVICE_12);
        }
    }

    /**
     * Method delete persisted entity from database by unique id
     * @param id unique identifier of entity
     * @return result of operation
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Override
    public Boolean deleteCustomerType(Long id) throws ServiceException {
        try {
            customerTypeDao.delete(customerTypeDao.get(id));
            logger.info(DELETE_CUSTOMER_TYPE + id);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceExceptionCode.IS_SERVICE_13);
        }
    }
}
