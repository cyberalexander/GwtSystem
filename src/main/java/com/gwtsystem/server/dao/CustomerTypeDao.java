package com.gwtsystem.server.dao;


import com.gwtsystem.domain.CustomerType;
import com.gwtsystem.server.dao.exception.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.List;

import static com.gwtsystem.server.dao.exception.DaoExceptionCode.IS_DAO_10;
import static com.gwtsystem.server.util.Constants.Const.*;
import static java.net.InetAddress.getLocalHost;

/**
 * Class of specific operations with CustomerType-entity
 * Created by alexanderleonovich on 01.08.15.
 */
@Repository
public class CustomerTypeDao extends GenericAbstractDao<CustomerType, Long> implements ICustomerTypeDao {
    private static Logger logger = Logger.getLogger(CustomerTypeDao.class);


    public CustomerTypeDao() {
        super(CustomerType.class);
    }

    /**
     * Parse results for getAll-method in GenericAbstractDao
     *
     * @return - list of CustomerType-entities
     * @throws DaoException - custom Exception class
     *                      for handle exceptions on DAO layer in application
     * @see com.gwtsystem.server.dao.GenericAbstractDao
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List<CustomerType> parseResultSet() throws DaoException {
        return getQuery(QUERY_GET_CUSTOMERSTYPES).list();
    }

    /**
     * Get CustomerType from database by unique caption
     *
     * @param customerTypeCaption - CustomerType Caprtion
     * @return CustomerType object
     * @throws DaoException - custom Exception class
     *                      for handle exceptions on DAO layer in application
     */
    @Override
    public CustomerType getByCaption(String customerTypeCaption) throws DaoException {
        try {
            logger.info(PREFIX + getLocalHost().getHostAddress() + GET_BY_CAPTION_MESSAGE);
            return (CustomerType) getQuery(QUERY_GET_BY_CAPTION)
                    .setParameter(PARAM, customerTypeCaption).uniqueResult();
        } catch (UnknownHostException | HibernateException e) {
            throw new DaoException(e, IS_DAO_10);
        }
    }
}
