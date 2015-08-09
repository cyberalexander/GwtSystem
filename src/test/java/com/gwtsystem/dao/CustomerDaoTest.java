package com.gwtsystem.dao;

import com.gwtsystem.domain.Customer;
import com.gwtsystem.domain.CustomerType;
import com.gwtsystem.server.dao.ICustomerDao;
import com.gwtsystem.server.dao.configuration.DaoConfiguration;
import com.gwtsystem.server.dao.exception.DaoException;
import org.hibernate.Query;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import java.util.ArrayList;
import java.util.List;

import static com.gwtsystem.util.TestConstants.TestConst.*;
import static org.junit.Assert.*;

/**
 * Test class for CRUD operations with Customer-entity.
 * Created 30.07.15.
 * @author Alexander Leonovich
 * @version 1.0
 */
@DataSet
@Transactional
public class CustomerDaoTest extends UnitilsJUnit4 {

    @SpringBeanByName
    private ICustomerDao customerDao;

    private Customer customerI;
    private Customer customerII;
    private Customer customerIII;

    @SpringApplicationContext
    public ConfigurableApplicationContext createAppContext() {
        return new AnnotationConfigApplicationContext(DaoConfiguration.class);
    }

    @Before
    public void setUp() throws Exception {
        customerI = new Customer(ONE, DR, NAME_1, NAME_METAPHONE_1, LASTNAME_1,
                LASTNAME_METAPHONE_1, new CustomerType(ONE, TYPECAPTION_1));
        customerII = new Customer(TWO, MR, NAME_2, NAME_METAPHONE_2, LASTNAME_2,
                LASTNAME_METAPHONE_2, new CustomerType(TWO, TYPECAPTION_2));
        customerIII = new Customer(THREE, MRS, NAME_3, LASTNAME_3,
                new CustomerType(THREE, TYPECAPTION_3));
    }

    @Test
    public void testGetCustomer() throws DaoException {
        Customer customer = customerDao.get(customerI.getCustomerId());
        assertNotNull(customer);
        assertEquals(customerI, customer);
    }

    @Test
    public void testGetAllCustomers() throws DaoException {
        List<Customer> customers = customerDao.getAll();
        assertNotNull(customers);
        assertTrue(customers.size() > ZERO);
    }

    @Test
    public void testAddCustomer() throws DaoException {
        Long customerId = customerDao.add(customerIII);
        assertNotNull(customerId);
    }

    @Test
    public void testUpdateCustomer() throws DaoException {
        customerI.setFirstName(customerII.getFirstName());
        customerDao.update(customerI);
        Customer customer = customerDao.get(customerI.getCustomerId());
        assertNotNull(customer);
        assertEquals(customerI, customer);
    }

    @Test
    public void testDeleteCustomer() throws DaoException {
        customerDao.delete(customerII);
        Customer customer = customerDao.get(customerII.getCustomerId());
        assertNull(customer);
    }

    @Test
    public void testFindCustomer() throws DaoException {
        Query query = customerDao.getQuery(FIND_CUSTOMER_QUERY);
        query.setParameter(PARAM, NAME_1);
        List<Customer> customers = query.list();
        assertNotNull(customers);
        assertTrue(customers.size() >= ONE);
    }

    @Test
    public void testCountCustomers() throws DaoException {
        Long result = customerDao.countCustomers();
        assertNotNull(result);
        assertTrue(result >= ONE);
    }

    @Test
    public void testGetLastModifiedCustomers() throws DaoException {
        List<Customer> customers = customerDao.getLastModifiedCustomers();
        assertNotNull(customers);
        assertTrue(customers.size() >= ONE);
    }

    @Test
    public void testGetCustomerByMetaPhones() throws DaoException{
        List<String> metaphones = new ArrayList<>(INT_TWO);
        metaphones.add(NAME_METAPHONE_1);
        metaphones.add(LASTNAME_METAPHONE_1);
        List<Customer> customers = customerDao.getCustomerByMetaPhones(metaphones);
        assertNotNull(customers);
        assertTrue(customers.size() == ONE);
    }
}
