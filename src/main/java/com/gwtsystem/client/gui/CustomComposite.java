package com.gwtsystem.client.gui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.gwtsystem.shared.dto.CustomerTypeDTO;

import java.util.List;

import static com.gwtsystem.client.util.ClientConstants.Const.M_FIELD_EMPTY;
import static com.gwtsystem.client.util.ClientConstants.Const.M_ONLY_EN;

/**
 * Custom Composite panel, what inherit other panels
 * in application
 * Created by alexanderleonovich on 05.08.15.
 */
public abstract class CustomComposite extends Composite {

    /**
     * List of customerTypes, what I use in
     * operations update & add new customer
     */
    private List<CustomerTypeDTO> customerTypeDTOs;

    public CustomComposite() {
    }

    public List<CustomerTypeDTO> getCustomerTypeDTOs() {
        return customerTypeDTOs;
    }

    public void setCustomerTypeDTOs(List<CustomerTypeDTO> customerTypeDTOs) {
        this.customerTypeDTOs = customerTypeDTOs;
    }

    /**
     * Private method, what check parameters, what
     * user input in fields, when he try add new Customer
     * Parameters must not be empty & only letters of english alphabet
     * @param title - Customer title
     * @param firstName - Customer firstName
     * @param lastName - Customer lastName
     * @return boolean result of checking parameters
     */
    public boolean checkInputParameters(String title, String firstName, String lastName){
        final String REGEXE = "^[A-Za-z]+$";
        if (title.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            Window.alert(M_FIELD_EMPTY);
        } else if (!(title.matches(REGEXE)) || !(firstName.matches(REGEXE)) ||
                !(lastName.matches(REGEXE))) {
            Window.alert(M_ONLY_EN);
        }else{
            return true;
        }
        return false;
    }
}
