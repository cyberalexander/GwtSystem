package com.gwtsystem.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.gwtsystem.client.service.ICustomerWebServiceClient;

import static com.gwtsystem.client.util.ClientConstants.Const.*;

/**
 * Page where contains a buttons for SEARCH ADD and GET ALL
 * customers
 * Created by alexanderleonovich on 05.08.15.
 */
public class MenuView extends CustomComposite {
    private MainView mainView;
    private ICustomerWebServiceClient serviceClient;
    private HorizontalPanel hPanel = new HorizontalPanel();

    /**
     * Constructor of MenuView where initialize
     * content of page
     * @param mainView - instance of base page
     */
    public MenuView(MainView mainView) {
        initWidget(this.hPanel);
        hPanel.setSpacing(5);
        this.mainView = mainView;
        serviceClient = mainView.getServiceClient();

        Button searchCustomerBtn = new Button(SEARCH);
        searchCustomerBtn.addClickHandler(new SearchClickHandler());
        this.hPanel.add(searchCustomerBtn);

        Button addCustomerButton = new Button(ADD);
        addCustomerButton.addClickHandler(new AddClickHandler());
        hPanel.add(addCustomerButton);

        Button getAllCustomersBtn = new Button(GET_ALL);
        getAllCustomersBtn.addClickHandler(new GetAllClickHandler());
        hPanel.add(getAllCustomersBtn);
    }

    /**
     * ClickHandler of search button
     * call method of MainView to adding search form
     * on a page
     */
    private class SearchClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent clickEvent) {
            mainView.searchCustomer();
        }
    }

    /**
     * ClickHandler for add button
     * call method of MainView to adding form
     * for Add new Customer
     */
    private class AddClickHandler implements ClickHandler{
        @Override
        public void onClick(ClickEvent clickEvent) {
            mainView.addCustomer();
        }
    }

    /**
     * ClickHandler for GetAll button
     * cal method of MainView to show all
     * Customers on page
     */
    private class GetAllClickHandler implements ClickHandler{
        @Override
        public void onClick(ClickEvent clickEvent) {
            serviceClient.getAllCustomerDTOs();
        }
    }

}
