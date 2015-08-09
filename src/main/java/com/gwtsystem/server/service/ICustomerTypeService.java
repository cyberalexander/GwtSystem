package com.gwtsystem.server.service;


import com.gwtsystem.server.service.exception.ServiceException;
import com.gwtsystem.shared.dto.CustomerTypeDTO;

import java.util.List;

/**
 * Specific intarface for CustomerType-service
 * @see com.gwtsystem.server.service.CustomerTypeService
 * Created by alexanderleonovich on 30.07.15.
 */
public interface ICustomerTypeService {

    /**
     * Get CustomerType persisted object from database
     * by unique id and convert it in DTO-object
     * @param id - id of entity in database
     * @return DTO copy of object
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    CustomerTypeDTO getCustomerTypeDTO(Long id) throws ServiceException;

    /**
     * Get CustomerType persisted object from database
     * by unique caption String and convert it in DTO-object
     * @param customerTypeCaption - caption of entity in database
     * @return DTO copy of object
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    CustomerTypeDTO getCustomerTypeDTObyCaption(String customerTypeCaption) throws ServiceException;

    /**
     * Get all CustomerTypes entities from database and
     * convert it in DTO objects
     * @return List of DTO objects
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    List<CustomerTypeDTO> getAllCustomerTypeDTOs() throws ServiceException;

    /**
     * Save new instance of CustomerType after convertation it from
     * DTO object
     * @param customerTypeDTO object for convertation in CustomerType entity
     * @return result of operation
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    Boolean saveCustomerType(CustomerTypeDTO customerTypeDTO) throws ServiceException;

    /**
     * Update instance of CustomerType, what contains in
     * database after convertation it from DTO object
     * @param customerTypeDTO object for convertation in customerType entity
     * @return result of operation
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    Boolean updateCustomerType(CustomerTypeDTO customerTypeDTO) throws ServiceException;

    /**
     * Delete persisted entity from database by unique id
     * @param id unique identifier of entity
     * @return result of operation
     * @throws ServiceException - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    Boolean deleteCustomerType(Long id) throws ServiceException;
}
