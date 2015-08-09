package com.gwtsystem.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.gwtsystem.client.service.ICustomerWebServiceClient;
import com.gwtsystem.shared.dto.CustomerDTO;
import com.gwtsystem.shared.dto.CustomerTypeDTO;

import java.util.List;

import static com.gwtsystem.client.util.ClientConstants.Const.*;

/**
 * Panel, what include in MainView-panel,
 * when user push the button "add customer"
 * At this panel placed Form for adding new Customer
 * Created by alexanderleonovich on 05.08.15.
 */
public class AddCustomerPage extends CustomComposite {
    private MainView mainView;
    private ICustomerWebServiceClient serviceClient;
    private VerticalPanel vPanel = new VerticalPanel();

    private TextBox titleTextBox;
    private TextBox firstNameTextBox;
    private TextBox lastNameTextBox;
    private ListBox dropBox;

    /**
     * In this constructor initialized
     * fields of form adding new customer
     * @param mainView - Parent panel of UI
     */
    public AddCustomerPage(MainView mainView) {
        this.mainView = mainView;
        initWidget(this.vPanel);
        vPanel.setSpacing(TEN);
        this.serviceClient = mainView.getServiceClient();

        HorizontalPanel headerPnl = new HorizontalPanel();
        HorizontalPanel customerTitlePnl = new HorizontalPanel();
        HorizontalPanel customerFirstNamePnl = new HorizontalPanel();
        HorizontalPanel CustomerlastNamePnl = new HorizontalPanel();
        HorizontalPanel customerTypeDropBoxPnl = new HorizontalPanel();
        HorizontalPanel addBtnPnl = new HorizontalPanel();

        Label headerLbl = new Label(L_ADD_MENU);
        headerLbl.setWidth("100");

        headerPnl.add(headerLbl);
        vPanel.add(headerPnl);

        Label itleLabel = new Label(L_TITLE);
        itleLabel.setWidth("100");

        titleTextBox = new TextBox();
        titleTextBox.setMaxLength(3);
        titleTextBox.setWidth("150");

        customerTitlePnl.add(itleLabel);
        customerTitlePnl.add(titleTextBox);

        vPanel.add(customerTitlePnl);

        Label firstNameLabel = new Label(L_F_NAME);
        firstNameLabel.setWidth("100");

        firstNameTextBox = new TextBox();
        firstNameTextBox.setWidth("150");

        customerFirstNamePnl.add(firstNameLabel);
        customerFirstNamePnl.add(firstNameTextBox);

        vPanel.add(customerFirstNamePnl);

        Label lastNameLabel = new Label(L_L_NAME);
        lastNameLabel.setWidth("100");

        lastNameTextBox = new TextBox();
        lastNameTextBox.setWidth("150");

        CustomerlastNamePnl.add(lastNameLabel);
        CustomerlastNamePnl.add(lastNameTextBox);

        vPanel.add(CustomerlastNamePnl);

        Label dropBoxLabel = new Label(L_TYPE);
        dropBoxLabel.setWidth("100");

        List<CustomerTypeDTO> customerTypeDTOs = mainView.getCustomerTypeDTOs();
        // Add a drop box with the list types
        dropBox = new ListBox();
        dropBox.setMultipleSelect(false);
        for (CustomerTypeDTO customerTypeDTO : customerTypeDTOs) {
            dropBox.addItem(customerTypeDTO.getCustomerTypeCaption());
        }

        customerTypeDropBoxPnl.add(dropBoxLabel);
        customerTypeDropBoxPnl.add(dropBox);

        vPanel.add(customerTypeDropBoxPnl);

        Button saveBtn = new Button(SAVE);
        saveBtn.setWidth("250");
        saveBtn.addClickHandler(new SaveClickHandler());
        addBtnPnl.add(saveBtn);
        vPanel.add(addBtnPnl);
    }

    /**
     * Handler of Save button
     */
    private class SaveClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent clickEvent) {
            String title = titleTextBox.getText();
            String firstName = firstNameTextBox.getText();
            String lastName = lastNameTextBox.getText();
            String type = dropBox.getSelectedValue();
            if (checkInputParameters(title, firstName, lastName)) {
                CustomerDTO customerDTO = new CustomerDTO(title, firstName, lastName, type);
                serviceClient.addCustomerDTO(customerDTO);
            }
        }
    }
}
