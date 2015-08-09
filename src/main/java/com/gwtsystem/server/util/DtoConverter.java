package com.gwtsystem.server.util;

import com.gwtsystem.domain.Customer;
import com.gwtsystem.domain.CustomerType;
import com.gwtsystem.shared.dto.CustomerDTO;
import com.gwtsystem.shared.dto.CustomerTypeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.gwtsystem.server.util.Constants.Const.ZERO;

/**
 * Custom class - converter from Persisted entity to DTO object
 * Created by alexanderleonovich on 03.08.15.
 */
@Component
public class DtoConverter {

    public DtoConverter() {
    }

    /**
     * Method create new instance of DTO object form
     * persisted instance of CustomerType entity
     * @param customerType object for convertation
     * @return DTO CustomerType object
     */
    public CustomerTypeDTO createCustomerTypeDTO(CustomerType customerType) {
        return new CustomerTypeDTO(
                customerType.getCustomerTypeId(),
                customerType.getCustomerTypeCaption());
    }

    /**
     * Method create new instance of DTO object form
     * persisted instance of Customer entity
     * @param customer object for convertation
     * @return DTO Customer object
     */
    public CustomerDTO createCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO(
                customer.getCustomerId(),
                customer.getTitle(),
                customer.getFirstName(),
                customer.getFirstNameMetaphone(),
                customer.getLastName(),
                customer.getLastNameMetaphone());
        if (null != customer &&
                null != customer.getCustomerType() &&
                null != customer.getCustomerType().getCustomerTypeCaption()){
            customerDTO.setCustomerType(customer.getCustomerType().getCustomerTypeCaption());
        }
        return customerDTO;
    }

    /**
     * Util method for convert List Persisted ojects
     * in DTO objects
     * @param customers list of entities
     * @return list of DTO objects
     */
    public List<CustomerDTO> convertToDTOlist(List<Customer> customers){
        List<CustomerDTO> customerDTOs = new ArrayList<>(
                customers != null ? customers.size() : ZERO);
        if (customers != null) {
            for (Customer customer : customers) {
                customerDTOs.add(createCustomerDTO(customer));
            }
        }
        return customerDTOs;
    }

    /**
     * Util method for convert List Persisted ojects
     * in DTO objects
     * @param customerTypes list of entities
     * @return list of DTO objects
     */
    public List<CustomerTypeDTO> convertToTypeDTOlist(List<CustomerType> customerTypes){
        List<CustomerTypeDTO> customerTypeDTOs = new ArrayList<>(
                customerTypes != null ? customerTypes.size() : ZERO);
        if (customerTypes != null) {
            for (CustomerType customerType : customerTypes) {
                customerTypeDTOs.add(createCustomerTypeDTO(customerType));
            }
        }
        return customerTypeDTOs;
    }
}
