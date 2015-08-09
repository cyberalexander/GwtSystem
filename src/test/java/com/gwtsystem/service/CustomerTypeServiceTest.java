package com.gwtsystem.service;


import com.gwtsystem.domain.CustomerType;
import com.gwtsystem.server.dao.ICustomerTypeDao;
import com.gwtsystem.server.dao.exception.DaoException;
import com.gwtsystem.server.service.CustomerTypeService;
import com.gwtsystem.server.service.ICustomerTypeService;
import com.gwtsystem.server.service.exception.ServiceException;
import com.gwtsystem.server.util.DtoConverter;
import com.gwtsystem.shared.dto.CustomerTypeDTO;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.gwtsystem.util.TestConstants.TestConst.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Test class for test methods in CustomerTypeService.class. Mockito used.
 * @see com.gwtsystem.server.service.CustomerTypeService
 * Created 30.07.15.
 * @author Alexander Leonovich
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerTypeServiceTest {
    private static Logger logger = Logger.getLogger(CustomerServiceTest.class);

    @Mock
    private DtoConverter converter;
    @Mock
    private ICustomerTypeDao customerTypeDao;
    @InjectMocks
    private ICustomerTypeService customerTypeService = new CustomerTypeService();

    private CustomerType customerTypeI;
    private CustomerTypeDTO customerTypeDTOI;
    private CustomerType customerTypeII;
    private CustomerTypeDTO customerTypeDTOII;
    private CustomerType customerTypeIII;
    private CustomerTypeDTO customerTypeDTOIII;
    private List<CustomerTypeDTO> customerTypeDTOs;

    @Before
    public void setUp() throws Exception {
        customerTypeI = new CustomerType(ONE, TYPECAPTION_1);
        customerTypeDTOI = new CustomerTypeDTO(ONE, TYPECAPTION_1);
        customerTypeII = new CustomerType(TWO, TYPECAPTION_2);
        customerTypeDTOII = new CustomerTypeDTO(TWO, TYPECAPTION_2);
        customerTypeIII = new CustomerType(THREE, TYPECAPTION_3);
        customerTypeDTOIII = new CustomerTypeDTO(THREE, TYPECAPTION_3);
        customerTypeDTOs = Arrays.asList(customerTypeDTOI, customerTypeDTOII);
    }

    @Test
    public void testGetCustomerTypeDTOStub() throws DaoException, ServiceException {
        try {
            stub(customerTypeService.getCustomerTypeDTO(customerTypeI.getCustomerTypeId()))
                    .toReturn(customerTypeDTOI);
            assertEquals(customerTypeDTOI, customerTypeService
                    .getCustomerTypeDTO(customerTypeI.getCustomerTypeId()));
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    @Test
    public void testGetCustomerTypeDTOVerify() throws DaoException, ServiceException {
        try {
            customerTypeService.getCustomerTypeDTO(anyLong());
            verify(customerTypeDao).get(anyLong());
        } catch (DaoException | ServiceException e) {
            logger.error(e);
        }
    }

    @Test(expected = ServiceException.class)
    public void testGetCustomerTypeDTOException() throws DaoException, ServiceException {
        when(customerTypeDao.get(anyLong())).thenThrow(new DaoException());
        customerTypeService.getCustomerTypeDTO(anyLong());
    }

    @Test
    public void testGetCustomerTypeDTObyCaption() throws ServiceException{
        try {
            when(customerTypeDao.getByCaption(TYPECAPTION_1)).thenReturn(any(CustomerType.class));
            customerTypeService.getCustomerTypeDTObyCaption(TYPECAPTION_1);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    /**
     * Magic test. Be careful.
     * @throws DaoException  - custom Exception class
     * for handle exceptions on DAO layer in application
     * @throws ServiceException  - custom Exception class
     * for handle exceptions on SERVICE layer in application
     */
    @Test
    public void testGetAllCustomerTypesStub() throws DaoException, ServiceException {
        try {
            stub(customerTypeService.getAllCustomerTypeDTOs()).toReturn(customerTypeDTOs);
            assertEquals(customerTypeDTOs, customerTypeService.getAllCustomerTypeDTOs());
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    @Test
    public void testGetCustomerTypesVerify() throws DaoException, ServiceException {
        try {
            customerTypeService.getAllCustomerTypeDTOs();
            verify(customerTypeDao).getAll();
        } catch (DaoException | ServiceException e) {
            logger.error(e);
        }
    }

    @Test(expected = ServiceException.class)
    public void testGetCustomerTypesException() throws DaoException, ServiceException {
        when(customerTypeDao.getAll()).thenThrow(new DaoException());
        customerTypeService.getAllCustomerTypeDTOs();
    }

    @Test
    public void testSaveCustomerTypeDTOVerify() throws DaoException, ServiceException{
        try {
            customerTypeService.saveCustomerType(customerTypeDTOIII);
            verify(customerTypeDao).add(customerTypeIII);
        } catch (DaoException | ServiceException e) {
            logger.error(e);
        }
    }

    @Test
    public void testUpdateCustomerTypeDTOVerify() throws DaoException, ServiceException{
        customerTypeI.setCustomerTypeCaption(customerTypeII.getCustomerTypeCaption());
        customerTypeDTOI.setCustomerTypeCaption((customerTypeI.getCustomerTypeCaption()));
        try {
            customerTypeService.updateCustomerType(customerTypeDTOI);
            verify(customerTypeDao).update(customerTypeI);
        } catch (DaoException | ServiceException e) {
            logger.error(e);
        }
    }

    @Test
    public void testDeleteCustomerTypeDTOVerify() throws DaoException, ServiceException{
        try {
            doNothing().when(customerTypeDao).delete(customerTypeII);
            assertTrue(customerTypeService
                    .deleteCustomerType(customerTypeII.getCustomerTypeId()));
        } catch (DaoException | ServiceException e) {
            logger.error(e);
        }
    }

}
