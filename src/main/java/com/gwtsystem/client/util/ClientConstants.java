package com.gwtsystem.client.util;

/**
 * My global server list of constants
 * Created by alexanderleonovich on 30.07.15.
 */
public class ClientConstants {

    public static class Const {

        /* BUTTONS AND LABELS */
        public static final String SEARCH = "Search Customer";
        public static final String ADD = "Add New Customer";
        public static final String GET_ALL = "Get All Customers";
        public static final String UPDATE = "Update Customer";
        public static final String DELETE = "Delete Customer";
        public static final String SAVE = "Save Customer";
        public static final String EDIT = "Edit Customer";

        public static final String L_CHOOSE = "Choose customer for update or delete:";
        public static final String L_ADD_MENU = "ADD MENU";
        public static final String L_EDIT_MENU = "EDIT MENU";
        public static final String L_SEARCH_MENU = "SEARCH MENU";
        public static final String L_ID = "ID";
        public static final String L_TITLE = "Title ";
        public static final String L_F_NAME = "First Name ";
        public static final String L_L_NAME = "Last Name";
        public static final String L_TYPE = "Customer Type: ";

        /*  MESSAGES AND FIELDS  */
        public static final String URL_IS = "URL is => ";
        public static final String REGEXE_ONE = "^[A-Za-z]+$";
        public static final int MINUS_ONE = -1;
        public static final int ZERO = 0;
        public static final int ONE = 1;
        public static final int TWO = 2;
        public static final int TEN = 10;
        public static final String M_CUSTOMER_SAVED = "Customer saved! Congratulations!";
        public static final String M_CUSTOMER_UPDATED = "Customer udated! Congratulations!";
        public static final String M_CUSTOMER_DELETED = "Customer deleted! Congratulations!";
        public static final String M_FIELD_EMPTY = "All fields must be filled!";
        public static final String M_ONLY_EN = "Valid only letters of the English alphabet!";

        /* ERRORS */
        public static final String ERROR_GET_BY_ID = "An error has occured when application try get Customer by ID!";
        public static final String ERROR_FIND_CUSTOMER = "An error has occured when application try Find Customers!";
        public static final String ERROR_NO_RESULTS = "No results equals for search parameters!";
        public static final String ERROR_SAVE_CUSTOMER = "ERROR SAVE CUSTOMER!";
        public static final String ERROR_UPDATE_CUSTOMER = "ERROR UPDATE CUSTOMER!";
        public static final String ERROR_DELETE_CUSTOMER = "ERROR DELETE CUSTOMER!";
        public static final String ERROR_GET_CUSTOMERS = "An error has occuerd when application try get all customers DTO!";
        public static final String ERROR_GET_CUSTOMERTYPES = "An error has occuerd when application try get all customerTypes DTO!";
    }
}
