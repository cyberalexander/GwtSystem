package com.gwtsystem.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.gwtsystem.client.gui.MainView;
import com.gwtsystem.shared.dto.CustomerDTO;
import com.gwtsystem.shared.dto.CustomerTypeDTO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.gwtsystem.client.util.ClientConstants.Const.*;

/**
 * Custom gwt service implementation of Servlet
 * Created by alexanderleonovich on 05.08.15.
 */
public class CustomerWebServiceClientImpl implements ICustomerWebServiceClient {
    Logger logger = Logger.getLogger("");

    private MainView mainView;
    private ICustomerWebServiceAsync customerWebService;

    public CustomerWebServiceClientImpl(String url) {
        logger.log(Level.SEVERE, URL_IS + url);
        this.customerWebService = GWT.create(ICustomerWebService.class);
        ServiceDefTarget endPoint = (ServiceDefTarget) this.customerWebService;
        endPoint.setServiceEntryPoint(url);

        this.mainView = new MainView(this);
    }

    /**
     * Method retrurn instance of MainView page
     * Head page of application
     *
     * @return instance of MainView
     */
    @Override
    public MainView getMainView() {
        return mainView;
    }

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to get Customer DTO from server
     *
     * @param id - unique identifier of persistence object
     */
    @Override
    public void getCustomerDTObyId(Long id) {
        customerWebService.getCustomerDTObyId(id, new GetByIdCallBack());
    }

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to get List of Customers DTO from server or for unique instance
     * by parameters for search
     *
     * @param searchParams - parameters for search
     */
    @Override
    public void findCustomerDTOs(List<String> searchParams) {
        customerWebService.findCustomerDTOs(searchParams, new FindCallBack());
    }

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to add instance of Customer in persistent state and save it in
     * database
     *
     * @param customerDTO - object for save in database
     */
    @Override
    public void addCustomerDTO(CustomerDTO customerDTO) {
        customerWebService.addCustomerDTO(customerDTO, new SaveCallBack());
    }

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to update instance of Customer, what contains in database
     *
     * @param customerDTO - instance, what we want to update
     */
    @Override
    public void updateCustomerDTO(CustomerDTO customerDTO) {
        customerWebService.updateCustomerDTO(customerDTO, new UpdateCallBack());
    }

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to delete instance of Customer, what contains in database  by
     * unique identifier of this object
     *
     * @param id - unique identifier of persistence object
     */
    @Override
    public void deleteCustomerDTO(Long id) {
        customerWebService.deleteCustomerDTO(id, new DeleteCallBack());
    }

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to get List of Customers DTO from server
     */
    @Override
    public void getAllCustomerDTOs() {
        customerWebService.getAllCustomerDTOs(new GetCustomerDTOsCallBack());
    }

    /**
     * Method call async  method of ICustomerWebServiceAsync interface
     * to get List of CustomerTypes DTO from server
     */
    @Override
    public void getCustomerTypeDTOs() {
        customerWebService.getCustomerTypeDTOs(new GetCustomerTypeDTOsCallBack());
    }

    /**
     * Custom implementation of AsyncCallback interface,
     * what handle result of operation from server and
     * send variant of response to client
     * this callback handle result of operation of getting
     * Customer from server by unique ID
     */
    private class GetByIdCallBack implements AsyncCallback<CustomerDTO> {
        @Override
        public void onFailure(Throwable throwable) {
            Window.alert(ERROR_GET_BY_ID);
        }

        @Override
        public void onSuccess(CustomerDTO customerDTO) {
            mainView.updateCustomer(customerDTO);
        }
    }

    /**
     * Custom implementation of AsyncCallback interface,
     * what handle result of operation from server and
     * send variant of response to client
     * This Callback handle result of finding object of
     * Customer in database
     */
    private class FindCallBack implements AsyncCallback<List<CustomerDTO>> {
        @Override
        public void onFailure(Throwable throwable) {
            Window.alert(ERROR_FIND_CUSTOMER);
        }

        @Override
        public void onSuccess(List<CustomerDTO> customerDTOs) {
            if (null != customerDTOs && customerDTOs.size() > 0) {
                mainView.showCustomers(customerDTOs);
            } else {
                Window.alert(ERROR_NO_RESULTS);
            }
        }
    }

    /**
     * Custom implementation of AsyncCallback interface,
     * what handle result of operation from server and
     * send variant of response to client
     * This Callback handle result of operation on server
     * to saving customer in database
     */
    private class SaveCallBack implements AsyncCallback<Boolean> {
        @Override
        public void onFailure(Throwable throwable) {
            Window.alert(ERROR_SAVE_CUSTOMER);
        }

        @Override
        public void onSuccess(Boolean result) {
            if (result) {
                String allIsOk = M_CUSTOMER_SAVED;
                mainView.showResultReport(allIsOk);
            } else {
                Window.alert(ERROR_SAVE_CUSTOMER);
            }
        }
    }

    /**
     * Custom implementation of AsyncCallback interface,
     * what handle result of operation from server and
     * send variant of response to client
     * This Callback handle result of operation on server
     * to updatig instance of customer in database
     */
    private class UpdateCallBack implements AsyncCallback<Boolean> {
        @Override
        public void onFailure(Throwable throwable) {
            Window.alert(ERROR_UPDATE_CUSTOMER);
        }

        @Override
        public void onSuccess(Boolean result) {
            if (result) {
                String allIsOk = M_CUSTOMER_UPDATED;
                mainView.showResultReport(allIsOk);
            } else {
                Window.alert(ERROR_UPDATE_CUSTOMER);
            }
        }
    }

    /**
     * Custom implementation of AsyncCallback interface,
     * what handle result of operation from server and
     * send variant of response to client
     * This Callback handle result of operation on server
     * to delete persistence instance of customer from database
     */
    private class DeleteCallBack implements AsyncCallback<Boolean> {
        @Override
        public void onFailure(Throwable throwable) {
            Window.alert(ERROR_DELETE_CUSTOMER);
        }

        @Override
        public void onSuccess(Boolean result) {
            if (result) {
                String allIsOk = M_CUSTOMER_DELETED;
                mainView.showResultReport(allIsOk);
            } else {
                Window.alert(ERROR_DELETE_CUSTOMER);
            }
        }
    }

    /**
     * Custom implementation of AsyncCallback interface,
     * what handle result of operation from server and
     * send variant of response to client
     * This Callback handle result of operation on server
     * to getting all Customers from database
     */
    private class GetCustomerDTOsCallBack implements AsyncCallback<List<CustomerDTO>> {
        @Override
        public void onFailure(Throwable throwable) {
            Window.alert(ERROR_GET_CUSTOMERS);
        }
        @Override
        public void onSuccess(List<CustomerDTO> result) {
            mainView.showCustomers(result);
        }
    }

    /**
     * Custom implementation of AsyncCallback interface,
     * what handle result of operation from server and
     * send variant of response to client
     * This Callback handle result of operation on server
     * to getting all CustomerTypes from database
     */
    private class GetCustomerTypeDTOsCallBack implements AsyncCallback<List<CustomerTypeDTO>> {
        @Override
        public void onFailure(Throwable throwable) {
            Window.alert(ERROR_GET_CUSTOMERTYPES);
        }
        @Override
        public void onSuccess(List<CustomerTypeDTO> result) {
            mainView.setCustomerTypeDTOs(result);
        }
    }
}
