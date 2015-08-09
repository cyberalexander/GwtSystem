package com.gwtsystem.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.gwtsystem.shared.dto.CustomerDTO;
import com.gwtsystem.shared.dto.CustomerTypeDTO;

import java.util.List;

/**
 * Client interface for calling server metods
 * Created by alexanderleonovich on 02.08.15.
 */
@RemoteServiceRelativePath("springGwtServices/customerWebService")
public interface ICustomerWebService extends RemoteService {

    CustomerDTO getCustomerDTObyId(Long id);

    List<CustomerDTO> findCustomerDTOs(List<String> searchParams);

    Boolean addCustomerDTO(CustomerDTO customerDTO);

    Boolean updateCustomerDTO(CustomerDTO customerDTO);

    Boolean deleteCustomerDTO(Long id);

    List<CustomerDTO> getAllCustomerDTOs();

    List<CustomerTypeDTO> getCustomerTypeDTOs();

}
