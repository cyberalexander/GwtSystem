package com.gwtsystem.client.service;

import com.gwtsystem.client.gui.MainView;
import com.gwtsystem.shared.dto.CustomerDTO;

import java.util.List;

/**
 * Custom interface, what implements my custom servlet
 * @see com.gwtsystem.client.service.CustomerWebServiceClientImpl
 * Created by alexanderleonovich on 05.08.15.
 */
public interface ICustomerWebServiceClient {

    /**
     * Method retrurn instance of MainView page
     * Head page of application
     * @return instance of MainView
     */
    MainView getMainView();

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to get Customer DTO from server
     * @param id - unique identifier of persistence object
     */
    void getCustomerDTObyId(Long id);

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to get List of Customers DTO from server or for unique instance
     * by parameters for search
     * @param searchParams - parameters for search
     */
    void findCustomerDTOs(List<String> searchParams);

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to add instance of Customer in persistent state and save it in
     * database
     * @param customerDTO  - object for save in database
     */
    void addCustomerDTO(CustomerDTO customerDTO);

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to update instance of Customer, what contains in database
     * @param customerDTO - instance, what we want to update
     */
    void updateCustomerDTO(CustomerDTO customerDTO);

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to delete instance of Customer, what contains in database  by
     * unique identifier of this object
     * @param id - unique identifier of persistence object
     */
    void deleteCustomerDTO(Long id);

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to get List of Customers DTO from server
     */
    void getAllCustomerDTOs();

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to get List of CustomerTypes DTO from server
     */
    void getCustomerTypeDTOs();
}
