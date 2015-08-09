package com.gwtsystem.client.gui;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.gwtsystem.shared.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

import static com.gwtsystem.client.util.ClientConstants.Const.*;
import static java.lang.String.valueOf;

/**
 * Pagination logic get from:
 * http://www.mytechtip.com/2010/11/gwt-celltable-example-using_8168.html
 * Created by alexanderleonovich on 05.08.15.
 */
public class ShowCustomersPage extends CustomComposite {
    private MainView mainView;
    private VerticalPanel vPanel = new VerticalPanel();

    /**
     * Constructor of page - "Show Customers" with pagination
     * laogic for display input customers
     * @param customerDTOs - list of customers for display on page
     */
    public ShowCustomersPage(MainView mainView, final List<CustomerDTO> customerDTOs) {
        initWidget(this.vPanel);
        vPanel.setSpacing(TEN);
        vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        this.mainView = mainView;

        final ArrayList<CustomerDTO> customerDTOsArray = (ArrayList<CustomerDTO>) customerDTOs;

        final CellTable<CustomerDTO> table = new CellTable<>();
        table.setPageSize(TEN);
        table.setWidth("700px");

        // Add a text column to show the id.
        TextColumn<CustomerDTO> idColumn = new TextColumn<CustomerDTO>() {
            @Override
            public String getValue(CustomerDTO object) {
                return valueOf(object.getCustomerId());
            }
        };
        table.addColumn(idColumn, L_ID);

        // Add a text column to show the title.
        TextColumn<CustomerDTO> titleColumn = new TextColumn<CustomerDTO>() {
            @Override
            public String getValue(CustomerDTO object) {
                return object.getTitle();
            }
        };
        table.addColumn(titleColumn, L_TITLE);

        // Add a text column to show the name.
        TextColumn<CustomerDTO> nameColumn = new TextColumn<CustomerDTO>() {
            @Override
            public String getValue(CustomerDTO object) {
                return object.getFirstName();
            }
        };
        table.addColumn(nameColumn, L_F_NAME);

        // Add a text column to show the last name.
        TextColumn<CustomerDTO> lastNameColumn = new TextColumn<CustomerDTO>() {
            @Override
            public String getValue(CustomerDTO object) {
                return object.getLastName();
            }
        };
        table.addColumn(lastNameColumn, L_L_NAME);

        // Add a text column to show the type.
        TextColumn<CustomerDTO> typeColumn = new TextColumn<CustomerDTO>() {
            @Override
            public String getValue(CustomerDTO object) {
                return object.getCustomerType();
            }
        };
        table.addColumn(typeColumn, L_TYPE);

        // Associate an async data provider to the table
        // XXX: Use AsyncCallback in the method onRangeChanged
        // to actaully get the data from the server side
        AsyncDataProvider<CustomerDTO> provider = new AsyncDataProvider<CustomerDTO>() {
            @Override
            protected void onRangeChanged(HasData<CustomerDTO> display) {
                int start = display.getVisibleRange().getStart();
                int end = start + display.getVisibleRange().getLength();
                end = end >= customerDTOsArray.size() ? customerDTOsArray.size() : end;
                List<CustomerDTO> sub = customerDTOsArray.subList(start, end);
                updateRowData(start, sub);
            }
        };
        provider.addDataDisplay(table);
        provider.updateRowCount(customerDTOs.size(), true);

        SimplePager pager = new SimplePager();
        pager.setDisplay(table);

        vPanel.add(table);
        vPanel.add(pager);

        /* instance of panel where user can choose user for delete or update */
        EditDeleteCustomerPanel panel = new EditDeleteCustomerPanel(mainView, customerDTOsArray);
        vPanel.add(panel);
    }
}
