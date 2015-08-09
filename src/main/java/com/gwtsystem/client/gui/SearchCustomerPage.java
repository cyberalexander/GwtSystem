package com.gwtsystem.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.gwtsystem.client.service.ICustomerWebServiceClient;

import java.util.ArrayList;
import java.util.List;

import static com.gwtsystem.client.util.ClientConstants.Const.*;

/**
 * Created by alexanderleonovich on 05.08.15.
 */
public class SearchCustomerPage extends CustomComposite {

    private MainView mainView;
    private VerticalPanel vPanel = new VerticalPanel();
    private ICustomerWebServiceClient serviceClient;
    private TextBox firstNameTextBox;
    private TextBox lastNameTextBox;

    public SearchCustomerPage(MainView mainView) {
        initWidget(this.vPanel);
        this.mainView = mainView;
        vPanel.setSpacing(TEN);
        serviceClient = mainView.getServiceClient();

        HorizontalPanel headerPnl = new HorizontalPanel();
        HorizontalPanel firstNamePanel = new HorizontalPanel();
        HorizontalPanel lastNamePanel = new HorizontalPanel();
        HorizontalPanel buttonPanel = new HorizontalPanel();


        Label headerLbl = new Label(L_SEARCH_MENU);
        headerLbl.setWidth("100");

        headerPnl.add(headerLbl);
        vPanel.add(headerPnl);

        Label firstNameLabel = new Label(L_F_NAME);
        firstNameLabel.setWidth("100");

        firstNameTextBox = new TextBox();
        firstNameTextBox.setWidth("150");

        firstNamePanel.add(firstNameLabel);
        firstNamePanel.add(firstNameTextBox);

        vPanel.add(firstNamePanel);

        Label lastNameLabel = new Label(L_L_NAME);
        lastNameLabel.setWidth("100");

        lastNameTextBox = new TextBox();
        lastNameTextBox.setWidth("150");

        lastNamePanel.add(lastNameLabel);
        lastNamePanel.add(lastNameTextBox);

        vPanel.add(lastNamePanel);

        Button searchBtn = new Button(SEARCH);
        searchBtn.setWidth("250");
        searchBtn.addClickHandler(new findCustomerBtnClickHandler());
        buttonPanel.add(searchBtn);
        vPanel.add(buttonPanel);
    }

    private class findCustomerBtnClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent clickEvent) {
            List<String> searchParams = new ArrayList<>(TWO);
            String firstName = firstNameTextBox.getText();
            String lastName = lastNameTextBox.getText();
            int checkResult = checkInputParameters(firstName, lastName);
            if (checkResult == ZERO) {
                serviceClient.findCustomerDTOs(searchParams);
            } else if (checkResult == ONE){
                searchParams.add(firstName);
                searchParams.add(lastName);
                serviceClient.findCustomerDTOs(searchParams);
            }
        }
    }

    private int checkInputParameters(String firstName, String lastName){
        final String REGEXE = REGEXE_ONE;
        if (firstName.isEmpty() && lastName.isEmpty()) {
            return ZERO;
        }else if(firstName.isEmpty() || lastName.isEmpty()){
            Window.alert(M_FIELD_EMPTY);
        }else if (!(firstName.matches(REGEXE)) || !(lastName.matches(REGEXE))) {
            Window.alert(M_ONLY_EN);
        }else{
            return ONE;
        }
        return MINUS_ONE;
    }
}
