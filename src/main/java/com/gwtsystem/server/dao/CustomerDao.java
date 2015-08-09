package com.gwtsystem.server.dao;

import com.gwtsystem.domain.Customer;
import com.gwtsystem.server.dao.exception.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.List;

import static com.gwtsystem.server.dao.exception.DaoExceptionCode.*;
import static com.gwtsystem.server.util.Constants.Const.*;
import static java.net.InetAddress.getLocalHost;

/**
 * Class of specific operations with Customer-entity
 * Created by alexanderleonovich on 01.08.15.
 */
@Repository
public class CustomerDao extends GenericAbstractDao<Customer, Long> implements ICustomerDao {
    private static Logger logger = Logger.getLogger(CustomerDao.class);

    public CustomerDao() {
        super(Customer.class);
    }

    /**
     * Parse results for getAll-method in GenericAbstractDao
     * @see com.gwtsystem.server.dao.GenericAbstractDao
     * @return - list of Customer-entities
     * @throws DaoException - custom Exception class
     * for handle exceptions on DAO layer in application
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List<Customer> parseResultSet() throws DaoException {
        return getQuery(QUERY_GET_CUSTOMERS).list();
    }

    /**
     * Method return count of Customers in database
     * @return - count of whole customers in database
     * @throws DaoException - custom Exception class
     * for handle exceptions on DAO layer in application
     */
    @Override
    public Long countCustomers() throws DaoException {
        try {
            logger.info(PREFIX + getLocalHost().getHostAddress() + COUNT_MESSAGE);
            Criteria criteria = getCurrentSession().createCriteria(Customer.class);
            criteria.setProjection(Projections.rowCount());
            return (Long) criteria.uniqueResult();
        } catch (UnknownHostException | HibernateException e) {
            throw new DaoException(e, IS_DAO_07);
        }
    }

    /**
     * Method return List of Customers Order by Last modified date
     * sort by descending
     * @return - List of Customers
     * @throws DaoException - custom Exception class
     * for handle exceptions on DAO layer in application
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> getLastModifiedCustomers() throws DaoException {
        try {
            logger.info(PREFIX + getLocalHost().getHostAddress() + LAST_MODIFIED_MESSAGE);
            List<Customer> customers = getQuery(QUERY_GET_LAST_MODIFIED).
                    setMaxResults(TEN).list();
            return customers;
        } catch (UnknownHostException | HibernateException e) {
            throw new DaoException(e, IS_DAO_08);
        }

    }

    /**
     * Method return List of Customers by input parameters
     * @param metaphones - list of metaphone -keys for search
     * @return list of Customers
     * @throws DaoException - custom Exception class
     * for handle exceptions on DAO layer in application
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> getCustomerByMetaPhones(List<String> metaphones) throws DaoException {
        try {
            logger.info(PREFIX + getLocalHost().getHostAddress() + METAPHONE_MESSAGE);
            Query query = getQuery(QUERY_GET_BY_METAPHONE);
            query.setParameter(FIRST_NAME_MATA, metaphones.get(ZERO));
            query.setParameter(LAST_NAME_MATA, metaphones.get(ONE));
            return query.list();
        } catch (UnknownHostException | HibernateException e) {
            throw new DaoException(e, IS_DAO_09);
        }
    }
}
