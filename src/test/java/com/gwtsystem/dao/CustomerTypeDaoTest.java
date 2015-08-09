package com.gwtsystem.dao;

import com.gwtsystem.domain.CustomerType;
import com.gwtsystem.server.dao.ICustomerTypeDao;
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

import java.util.List;

import static com.gwtsystem.util.TestConstants.TestConst.*;
import static org.junit.Assert.*;


/**
 * Test class for CRUD operations with CustomerType-entity.
 * Created 30.07.15.
 * @author Alexander Leonovich
 * @version 1.0
 */
@DataSet
@Transactional
public class CustomerTypeDaoTest extends UnitilsJUnit4 {

    @SpringBeanByName
    private ICustomerTypeDao customerTypeDao;

    private CustomerType customerTypeI;
    private CustomerType customerTypeII;
    private CustomerType customerTypeIII;

    @SpringApplicationContext
    public ConfigurableApplicationContext createAppContext() {
        return new AnnotationConfigApplicationContext(DaoConfiguration.class);
    }

    @Before
    public void setUp() throws Exception {
        customerTypeI = new CustomerType(ONE, TYPECAPTION_1);
        customerTypeII = new CustomerType(TWO, TYPECAPTION_2);
        customerTypeIII = new CustomerType(THREE, TYPECAPTION_3);
    }

    @Test
    public void testGetCustomerType() throws DaoException {
        CustomerType result = customerTypeDao.get(customerTypeI.getCustomerTypeId());
        assertNotNull(result);
        assertEquals(customerTypeI, result);
    }

    @Test
    public void testGetAllCustomerTypes() throws DaoException {
        List<CustomerType> customerTypes = customerTypeDao.getAll();
        assertNotNull(customerTypes);
        assertTrue(customerTypes.size() > ZERO);
    }

    @Test
    public void testAddCustomerType() throws DaoException {
        Long customerId = customerTypeDao.add(customerTypeIII);
        assertNotNull(customerId);
    }

    @Test
    public void testUpdateCustomerType() throws DaoException {
        customerTypeI.setCustomerTypeCaption(customerTypeII.getCustomerTypeCaption());
        customerTypeDao.update(customerTypeI);
        CustomerType customerType = customerTypeDao.get(customerTypeI.getCustomerTypeId());
        assertNotNull(customerType);
        assertEquals(customerTypeI, customerType);
    }

    @Test
    public void testDeleteCustomerType() throws DaoException {
        customerTypeDao.delete(customerTypeII);
        CustomerType customerType = customerTypeDao.get(customerTypeII.getCustomerTypeId());
        assertNull(customerType);
    }

    @Test
    public void testFindCustomerType() throws DaoException {
        Query query = customerTypeDao.getQuery(FIND_CUSTOMERTYPE_QUERY);
        query.setParameter(PARAM, TYPECAPTION_1);
        List<CustomerType> customerTypes = query.list();
        assertNotNull(customerTypes);
        assertTrue(customerTypes.size() >= ONE);
    }

    public void testGetByCaption() throws DaoException{
        CustomerType customerType = customerTypeDao.getByCaption(TYPECAPTION_1);
        assertNotNull(customerType);
    }
}
