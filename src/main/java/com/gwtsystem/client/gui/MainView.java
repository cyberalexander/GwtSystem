package com.gwtsystem.client.gui;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtsystem.client.service.CustomerWebServiceClientImpl;
import com.gwtsystem.shared.dto.CustomerDTO;

import java.util.List;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;

/**
 * Main view of the application
 * On this panel will be added other panels
 * Created by alexanderleonovich on 05.08.15.
 */
public class MainView extends CustomComposite {
    private CustomerWebServiceClientImpl serviceClient;
    private VerticalPanel vPanel = new VerticalPanel();
    private VerticalPanel contentPanel;
    private CustomComposite currentPage;

    /**
     * Constructor of MinView where initialize content
     * base of page, where will be contain all other pages
     * @param serviceClient client Servlet for handle operations
     *                      in applicaton
     */
    public MainView(CustomerWebServiceClientImpl serviceClient) {
        initWidget(vPanel);
        vPanel.setWidth("100%");
        vPanel.setHorizontalAlignment(ALIGN_CENTER);
        this.serviceClient = serviceClient;
        serviceClient.getCustomerTypeDTOs();

        VerticalPanel headerPnl = new VerticalPanel();
        Image header = new Image("/img/531.jpg");
        header.setWidth("100%");
        header.setHeight("200px");
        headerPnl.add(header);
        vPanel.add(headerPnl);

        MenuView menuView = new MenuView(this);
        vPanel.add(menuView);

        contentPanel = new VerticalPanel();
        contentPanel.setHorizontalAlignment(ALIGN_CENTER);


        Image getStart = new Image("/img/start.jpg");
        getStart.setWidth("300px");
        contentPanel.add(getStart);

        vPanel.add(contentPanel);

        VerticalPanel footerPnl = new VerticalPanel();
        Image footer = new Image("/img/534.jpg");
        footer.setWidth("100%");
        footer.setHeight("200px");
        footerPnl.add(footer);
        vPanel.add(footerPnl);

    }

    /**
     * Getter for instance of CustomerWebServiceClientImpl
     * @see com.gwtsystem.client.service.CustomerWebServiceClientImpl
     * @return instance of CustomerWebServiceClientImpl
     */
    public CustomerWebServiceClientImpl getServiceClient() {
        return serviceClient;
    }

    /**
     * Method add in MainView panel with form
     * for search Customer object in database
     */
    public void searchCustomer() {
        contentPanel.clear();
        currentPage = new SearchCustomerPage(this);
        contentPanel.add(currentPage);
        contentPanel.setCellHorizontalAlignment(currentPage, ALIGN_CENTER);
    }

    /**
     * Method add in MainView panel with pagination
     * for show results of search Customer object in database
     * or for show all customers what contains in database
     */
    public void showCustomers(List<CustomerDTO> customerDTOs) {
        contentPanel.clear();
        currentPage = new ShowCustomersPage(this, customerDTOs);
        contentPanel.add(currentPage);
        contentPanel.setCellHorizontalAlignment(currentPage, ALIGN_CENTER);
    }

    /**
     * Method add in MainView panel with form
     * for add new Customer object
     */
    public void addCustomer() {
        contentPanel.clear();
        currentPage = new AddCustomerPage(this);
        contentPanel.add(currentPage);
        contentPanel.setCellHorizontalAlignment(currentPage, ALIGN_CENTER);
    }

    /**
     * Method add in MainView panel with form
     * for update Customer instance what contains in database
     */
    public void updateCustomer(CustomerDTO customerDTO) {
        contentPanel.clear();
        currentPage = new EditCustomerPage(this, customerDTO);
        contentPanel.add(currentPage);
        contentPanel.setCellHorizontalAlignment(currentPage, ALIGN_CENTER);
    }

    /**
     * Method add in MainView panel with result
     * of save, update, delete Customer object in database
     */
    public void  showResultReport(String message){
        contentPanel.clear();
        Label result = new Label(message);
        result.addStyleName("gwt-Label");
        Image resultImg = new Image("/img/ok.jpg");
        resultImg.setWidth("400px");
        contentPanel.add(result);
        contentPanel.add(resultImg);
        contentPanel.setCellHorizontalAlignment(currentPage, ALIGN_CENTER);
    }
}
