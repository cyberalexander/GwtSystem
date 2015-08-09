package com.gwtsystem.util;

/**
 * Class with constant fields, what used in test-classes.
 * Created 30.07.15.
 * @author Alexander Leonovich
 * @version 1.0
 */
public class TestConstants {

    public static class TestConst {

        public static final int ZERO = 0;
        public static final int INT_TWO = 2;
        public static final Long ONE = 1L;
        public static final Long TWO = 2L;
        public static final Long THREE = 3L;

        public static final String DR = "Dr";
        public static final String NAME_1 = "Fred";
        public static final String NAME_METAPHONE_1 = "FRT";
        public static final String LASTNAME_1 = "Clark";
        public static final String LASTNAME_METAPHONE_1 = "KLRK";

        public static final String MR = "Mr";
        public static final String NAME_2 = "Anderson";
        public static final String NAME_METAPHONE_2 = "ANTRSN";
        public static final String LASTNAME_2 = "Lee";
        public static final String LASTNAME_METAPHONE_2 = "L";

        public static final String MRS = "Mrs";
        public static final String NAME_3 = "CustomerTestName3";
        public static final String LASTNAME_3 = "CustomerTestLastName3";

        public static final String TYPECAPTION_1 = "customerTypeCaption1";
        public static final String TYPECAPTION_2 = "customerTypeCaption2";
        public static final String TYPECAPTION_3 = "customerTypeCaption3";
        public static final String FIND_CUSTOMER_QUERY = "from Customer c where c.firstName like :param";
        public static final String FIND_CUSTOMERTYPE_QUERY = "from CustomerType ct where ct.customerTypeCaption like :param";
        public static final String PARAM = "param";


    }

}
