package com.gwtsystem.service;

import com.gwtsystem.domain.Customer;
import com.gwtsystem.domain.CustomerType;
import com.gwtsystem.server.dao.ICustomerDao;
import com.gwtsystem.server.dao.ICustomerTypeDao;
import com.gwtsystem.server.dao.exception.DaoException;
import com.gwtsystem.server.service.CustomerService;
import com.gwtsystem.server.service.ICustomerService;
import com.gwtsystem.server.service.exception.ServiceException;
import com.gwtsystem.server.util.DtoConverter;
import com.gwtsystem.server.util.MetaPhoneUtil;
import com.gwtsystem.shared.dto.CustomerDTO;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gwtsystem.util.TestConstants.TestConst.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Test class for test methods in CustomerService.class. Mockito used.
 * @see com.gwtsystem.server.service.CustomerService
 * Created 30.07.15.
 * @author Alexander Leonovich
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    private static Logger logger = Logger.getLogger(CustomerServiceTest.class);

    @Mock
    private MetaPhoneUtil metaphoneUtil;
    @Mock
    private ICustomerTypeDao customerTypeDao;
    @Mock
    private DtoConverter converter;
    @Mock
    private ICustomerDao customerDao;
    @InjectMocks
    private ICustomerService customerService = new CustomerService();

    private Customer customerI;
    private CustomerDTO customerDTOI;
    private Customer customerII;
    private CustomerDTO customerDTOII;
    private CustomerDTO customerDTOIII;
    private List<CustomerDTO> customerDTOs;

    @Before
    public void setUp() throws Exception {
        customerI = new Customer(ONE, DR, NAME_1, NAME_METAPHONE_1, LASTNAME_1,
                LASTNAME_METAPHONE_1, new CustomerType(ONE, TYPECAPTION_1));
        customerDTOI = new CustomerDTO(ONE, DR, NAME_1, NAME_METAPHONE_1, LASTNAME_1,
                LASTNAME_METAPHONE_1, TYPECAPTION_1);
        customerII = new Customer(TWO, MR, NAME_2, NAME_METAPHONE_2, LASTNAME_2,
                LASTNAME_METAPHONE_2, new CustomerType(TWO, TYPECAPTION_2));
        customerDTOII = new CustomerDTO(TWO, MR, NAME_2, NAME_METAPHONE_2, LASTNAME_2,
                LASTNAME_METAPHONE_2, TYPECAPTION_2);
        customerDTOIII = new CustomerDTO(THREE, MRS, NAME_3, LASTNAME_3);
        customerDTOs = Arrays.asList(customerDTOI, customerDTOII);
    }

    @Test
    public void testGetCustomerDTOStub() throws DaoException, ServiceException{
        try {
            stub(customerService.getCustomerDTO(customerDTOI.getCustomerId()))
                    .toReturn(customerDTOI);
            assertEquals(customerDTOI, customerService.getCustomerDTO(customerDTOI
                    .getCustomerId()));
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    @Test
    public void testGetCustomerDTOVerify() throws DaoException, ServiceException {
        try {
            customerService.getCustomerDTO(anyLong());
            verify(customerDao).get(anyLong());
        } catch (DaoException | ServiceException e) {
            logger.error(e);
        }
    }

    @Test(expected = ServiceException.class)
    public void testGetCustomerDTOException() throws DaoException, ServiceException {
        when(customerDao.get(anyLong())).thenThrow(new DaoException());
        customerService.getCustomerDTO(anyLong());
    }

    @Test
    public void testGetAllCustomerDTOsStub() throws DaoException, ServiceException{
        try{
            stub(customerService.getAllCustomerDTOs()).toReturn(customerDTOs);
            assertEquals(customerDTOs, customerService.getAllCustomerDTOs());
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    @Test
    public void testSaveCustomerVerify() throws DaoException, ServiceException{
        try {
            customerService.saveCustomer(customerDTOIII);
            verify(customerTypeDao).getByCaption(customerDTOIII.getCustomerType());
            verify(metaphoneUtil).metaphoneEncoding(any(Customer.class));
            verify(customerDao).add(any(Customer.class));
        } catch (DaoException | ServiceException e) {
            logger.error(e);
        }
    }

    @Test
    public void testUpdateCustomer() throws DaoException, ServiceException{
        customerI.setFirstName(customerII.getFirstName());
        customerDTOI.setFirstName((customerDTOI.getFirstName()));
        try {
            customerService.updateCustomer(customerDTOI);
            verify(customerDao).update(any(Customer.class));
        } catch (DaoException | ServiceException e) {
            logger.error(e);
        }
    }

    @Test
    public void testDeleteCustomer() throws DaoException, ServiceException {
        try {
            doNothing().when(customerDao).delete(customerII);
            assertTrue(customerService.deleteCustomer(customerII.getCustomerId()));
        } catch (DaoException | ServiceException e) {
            logger.error(e);
        }
    }

    @Test
    public void testFindCustomerDTOs() throws ServiceException{
        List<String> metaphones = new ArrayList<>(INT_TWO);
        metaphones.add(NAME_METAPHONE_1);
        metaphones.add(LASTNAME_METAPHONE_1);
        try {
            stub(customerService.findCustomerDTOs(metaphones))
                    .toReturn(customerDTOs);
            assertEquals(customerDTOs, customerService
                    .findCustomerDTOs(metaphones));
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    @Test
    public void testCountCustomers() throws ServiceException{
        try {
            stub(customerService.countCustomers()).toReturn(THREE);
            assertEquals(THREE, customerService.countCustomers());
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
