package com.gwtsystem.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtsystem.shared.dto.CustomerDTO;
import com.gwtsystem.shared.dto.CustomerTypeDTO;

import java.util.List;

/**
 * Async copy of client interface for calling server metods
 * Created by alexanderleonovich on 02.08.15.
 */
public interface ICustomerWebServiceAsync {

    void getCustomerDTObyId(Long id, AsyncCallback<CustomerDTO> async);

    void findCustomerDTOs(List<String> searchParams, AsyncCallback<List<CustomerDTO>> async);

    void addCustomerDTO(CustomerDTO customerDTO, AsyncCallback<Boolean> async);

    void updateCustomerDTO(CustomerDTO customerDTO, AsyncCallback<Boolean> async);

    void deleteCustomerDTO(Long id, AsyncCallback<Boolean> async);

    void getAllCustomerDTOs(AsyncCallback<List<CustomerDTO>> async);

    void getCustomerTypeDTOs(AsyncCallback<List<CustomerTypeDTO>> async);

}
