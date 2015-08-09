package com.gwtsystem.server.dao.configuration;


import com.gwtsystem.server.dao.CustomerDao;
import com.gwtsystem.server.dao.CustomerTypeDao;
import com.gwtsystem.server.dao.ICustomerDao;
import com.gwtsystem.server.dao.ICustomerTypeDao;
import org.apache.commons.codec.language.Metaphone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alexanderleonovich on 29.07.15.
 * @see com.gwtsystem.server.dao.GenericAbstractDao
 * Configuration class for GenericDao Spring beas
 */
@Configuration
public class BeansConfiguration {

    /**
     * Initialize GenericDao-Bean for work with Customer entity
     * @return GenericDao for Customer.class
     */
    @Bean(name = "customerDao")
    public ICustomerDao customerDao() {
        return new CustomerDao();
    }

    /**
     * Initialize GenericDao-Bean for work with CustomerType entity
     * @return GenericDao for CustomerType.class
     */
    @Bean(name = "customerTypeDao")
    public ICustomerTypeDao customerTypeDao() {
        return new CustomerTypeDao();
    }

    /**
     * Metaphone object bean
     * @return Metaphone instance
     */
    @Bean(name = "metaphone")
    public Metaphone metaphone(){
        Metaphone metaphone = new Metaphone();
        metaphone.setMaxCodeLen(10);
        return metaphone;
    }

}
