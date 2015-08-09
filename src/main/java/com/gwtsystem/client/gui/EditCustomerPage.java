package com.gwtsystem.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.gwtsystem.client.service.ICustomerWebServiceClient;
import com.gwtsystem.shared.dto.CustomerDTO;
import com.gwtsystem.shared.dto.CustomerTypeDTO;

import java.util.List;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;
import static com.gwtsystem.client.util.ClientConstants.Const.*;

/**
 * Panel, what include in MainView-panel,
 * when user push the button "update customer"
 * At this panel placed Form for updating Customer
 * Created by alexanderleonovich on 05.08.15.
 */
public class EditCustomerPage extends CustomComposite {
    private MainView mainView;
    private ICustomerWebServiceClient serviceClient;
    private VerticalPanel vPanel = new VerticalPanel();
    private TextBox titleTextBox;
    private TextBox firstNameTextBox;
    private TextBox lastNameTextBox;
    private ListBox dropBox;

    private CustomerDTO customerDTO;

    public EditCustomerPage(MainView mainView, CustomerDTO customerDTO) {
        this.mainView = mainView;
        initWidget(this.vPanel);
        vPanel.setSpacing(TEN);
        this.customerDTO = customerDTO;
        this.serviceClient = mainView.getServiceClient();

        HorizontalPanel header = new HorizontalPanel();

        Label headerLbl = new Label(L_EDIT_MENU);
        headerLbl.setWidth("100");

        header.add(headerLbl);
        vPanel.add(header);

        FlexTable table = new FlexTable();
        vPanel.add(table);
        vPanel.setCellHorizontalAlignment(table, ALIGN_CENTER);

        Label nameLbl = new Label(L_TITLE);
        table.setWidget(0, 0, nameLbl);

        Label ageLbl = new Label(L_F_NAME);
        table.setWidget(1, 0, ageLbl);

        Label genderLbl = new Label(L_L_NAME);
        table.setWidget(2, 0, genderLbl);

        Label newCustomerType = new Label(L_TYPE);
        table.setWidget(4, 0, newCustomerType);

        titleTextBox = new TextBox();
        titleTextBox.setText(customerDTO.getTitle());
        table.setWidget(0, 1, titleTextBox);

        firstNameTextBox = new TextBox();
        firstNameTextBox.setText(customerDTO.getFirstName());
        table.setWidget(1, 1, firstNameTextBox);

        lastNameTextBox = new TextBox();
        lastNameTextBox.setText(customerDTO.getLastName());
        table.setWidget(2, 1, lastNameTextBox);

        List<CustomerTypeDTO> customerTypeDTOs = mainView.getCustomerTypeDTOs();
        dropBox = new ListBox();
        dropBox.setMultipleSelect(false);
        for (int i = 0; i < customerTypeDTOs.size(); i++) {
            dropBox.addItem(customerTypeDTOs.get(i).getCustomerTypeCaption());
            if (customerDTO.getCustomerType().equals(customerTypeDTOs.get(i)
                    .getCustomerTypeCaption())){
                dropBox.setSelectedIndex(i);
            }
        }
        table.setWidget(4, 1, dropBox);

        Button editBtn = new Button(EDIT);
        editBtn.addClickHandler(new EditClickHandler());
        this.vPanel.add(editBtn);
        this.vPanel.setCellHorizontalAlignment(editBtn, ALIGN_CENTER);
    }

    private class EditClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent clickEvent) {
            String title = titleTextBox.getText();
            String firstName = firstNameTextBox.getText();
            String lastName = lastNameTextBox.getText();
            String type = dropBox.getSelectedValue();
            if (checkInputParameters(title, firstName, lastName)) {
                customerDTO.setTitle(title);
                customerDTO.setFirstName(firstName);
                customerDTO.setLastName(lastName);
                customerDTO.setCustomerType(type);
                serviceClient.updateCustomerDTO(customerDTO);
            }
        }
    }
}
