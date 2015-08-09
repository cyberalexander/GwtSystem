package com.gwtsystem.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.gwtsystem.client.service.ICustomerWebServiceClient;
import com.gwtsystem.shared.dto.CustomerDTO;

import java.util.List;

import static com.gwtsystem.client.util.ClientConstants.Const.*;
import static java.lang.String.valueOf;

/**
 * Created by alexanderleonovich on 06.08.15.
 */
public class EditDeleteCustomerPanel extends CustomComposite{
    private ICustomerWebServiceClient serviceClient;
    private List<CustomerDTO> customerDTOs;
    private MainView mainView;
    private HorizontalPanel hPanel = new HorizontalPanel();
    private ListBox dropBox;


    public EditDeleteCustomerPanel(MainView mainView, List<CustomerDTO> customerDTOs) {
        initWidget(this.hPanel);
        this.mainView = mainView;
        hPanel.setSpacing(TEN);
        hPanel.setStyleName("gwt-VerticalSplitPanel", true);
        hPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

        this.customerDTOs = customerDTOs;
        this.mainView = mainView;
        serviceClient = mainView.getServiceClient();

        Label dropBoxLabel = new Label(L_CHOOSE);
        dropBoxLabel.setWidth("230");
        // Add a drop box with the id of customers
        dropBox = new ListBox();
        dropBox.setMultipleSelect(false);
        for (CustomerDTO customerDTO : customerDTOs) {
            dropBox.addItem(valueOf(customerDTO.getCustomerId()));
        }

        Button updateCustomerBtn = new Button(UPDATE);
        updateCustomerBtn.addClickHandler(new UpdateClickHandler());

        Button deleteCustomerBtn = new Button(DELETE);
        deleteCustomerBtn.addClickHandler(new DeleteCustomerDTOClickHandler());

        hPanel.add(dropBoxLabel);
        hPanel.add(dropBox);
        hPanel.add(updateCustomerBtn);
        hPanel.add(deleteCustomerBtn);

    }

    /**
     * ClickHandler for update button
     * cal method of Service to get Customer by id
     * for updating this object
     */
    private class UpdateClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent clickEvent) {
            Long id = Long.valueOf(dropBox.getSelectedValue());
            serviceClient.getCustomerDTObyId(id);
        }
    }

    /**
     * ClickHandler for delete button
     * cal method of Service to delete Customer by id
     */
    private class DeleteCustomerDTOClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent clickEvent) {
            serviceClient.deleteCustomerDTO(Long.valueOf(dropBox.getSelectedValue()));
        }
    }
}
