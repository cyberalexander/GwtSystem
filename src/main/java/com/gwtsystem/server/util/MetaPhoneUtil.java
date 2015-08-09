package com.gwtsystem.server.util;

import com.gwtsystem.domain.Customer;
import org.apache.commons.codec.language.Metaphone;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * My custom Metaphone util class
 * using methods from Apache Metaphone library
 * Created by alexanderleonovich on 06.08.15.
 */
@Component("metaphoneUtil")
public class MetaPhoneUtil {
    private static Logger logger = Logger.getLogger(MetaPhoneUtil.class);

    @Autowired
    private Metaphone metaphone;

    public MetaPhoneUtil() {

    }

    /**
     * Get from Customer firstName and lastName and
     * convert it in metaphone keys, after add this keys
     * in Customer object
     * @param customer Customer entity
     * @return Customer entity with added metaphone keys
     */
    public Customer metaphoneEncoding(Customer customer) {
        String firstNameMeta = metaphone.encode(customer.getFirstName());
        String lastNameMeta = metaphone.encode(customer.getLastName());
        customer.setFirstNameMetaphone(firstNameMeta);
        customer.setLastNameMetaphone(lastNameMeta);
        logger.info("METAPHONES OF CUSTOMER: firstName=" + firstNameMeta + ", lastName=" + lastNameMeta + ".");
        return customer;
    }

    /**
     * Convert List with parameters in metaphone keys
     * @param searchParams parameters for convertation
     * @return converted metaphone keys
     */
    public List<String> convertToMetaphone(List<String> searchParams) {
        List<String> metaphones = new ArrayList<>(searchParams.size());
        for (String param : searchParams) {
            String metaCode = metaphone.encode(param);
            metaphones.add(metaCode);
            logger.info("METAPHONES FOR SEARCH: meta= " + metaCode + " .");
        }
        return metaphones;
    }
}
