package com.gwtsystem.server.util;

/**
 * My global server list of constants
 * Created by alexanderleonovich on 30.07.15.
 */
public class Constants {

    public static class Const {
        /*  MESSAGES  */
        public static final String PREFIX = "Action from IP ADDRESS: ";
        public static final String GET_MESSAGE = " -=- Getting object with id: ";
        public static final String GET_LIST_MESSAGE = " -=- Getting list of objects";
        public static final String ADD_MESSAGE = " -=- Adding object with id: ";
        public static final String UPDATE_MESSAGE = " -=- Updating object";
        public static final String DELETE_MESSAGE = " -=- Deleting object";
        public static final String GET_HQL_MESSAGE = " -=- Getting hql query";
        public static final String GET_BY_CAPTION_MESSAGE = " -=- Getting CustomerType by caption.";
        public static final String COUNT_MESSAGE = " -=- Count Customers.";
        public static final String LAST_MODIFIED_MESSAGE = " -=- Getting last modified customers.";
        public static final String METAPHONE_MESSAGE = " -=- Getting Customers by metaphone keys.";


        public static final String RECEIVED_CUSTOMER = "Received Customer: ";
        public static final String RECEIVE_ALL = "Receive all customers. Size of list = ";
        public static final String PERSIST_CUSTOMER = "Persist  Customer: ";
        public static final String UPDATING_CUSTOMER = "Updating customer: ";
        public static final String DELETE_CUSTOMER = "Deleting Customer: ";
        public static final String SEARCH_BY_EMPTY = "SEARCH RESULT BY EMPTY PARAMETERS: => ";
        public static final String SEARCH_BY_METAPHONES = "SEARCH RESULT BY METAPHONES: => ";
        public static final String GET_CUSTOMER_TYPE_1 = "Getting customerType by unique ID: ";
        public static final String GET_CUSTOMER_TYPE_2 = "Getting customerType by caption: ";
        public static final String GET_CUSTOMER_TYPES = "Getting all customerTypes in CustomerTypeService. List size ==> ";
        public static final String PERSIST_CUSTOMER_TYPE = "Adding customerType: ";
        public static final String UPDATE_CUSTOMER_TYPE = "Updating customerType: ";
        public static final String DELETE_CUSTOMER_TYPE = "CustomerType deleted from database! Id of deleted customerType is ";



        /* FIELD CONSTANTS */
        public static final String FIRST_NAME_MATA = "firstNameMetaphone";
        public static final String LAST_NAME_MATA = "lastNameMetaphone";
        public static final String PARAM = "param";
        public static final int TEN = 10;
        public static final int ZERO = 0;
        public static final int ONE = 1;

        /* QUERY CONSTANTS */
        public static final String QUERY_GET_CUSTOMERS = "SELECT c FROM Customer c";
        public static final String QUERY_GET_LAST_MODIFIED = "SELECT c FROM Customer c ORDER BY c.modifiedWhen DESC";
        public static final String QUERY_GET_BY_METAPHONE = "SELECT c FROM Customer c WHERE c.firstNameMetaphone like" +
                " :firstNameMetaphone and c.lastNameMetaphone like :lastNameMetaphone ORDER BY c.modifiedWhen DESC";
        public static final String QUERY_GET_CUSTOMERSTYPES = "SELECT ct FROM CustomerType ct";
        public static final String QUERY_GET_BY_CAPTION = "from CustomerType ct where ct.customerTypeCaption like :param ";
    }
}
