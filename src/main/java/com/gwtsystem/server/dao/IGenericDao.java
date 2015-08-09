package com.gwtsystem.server.dao;


import com.gwtsystem.server.dao.exception.DaoException;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alexanderleonovich on 30.07.15.
 * <p>Interface for generic CRUD operations on a repository for a specific type. </p>
 * @see com.gwtsystem.server.dao.GenericAbstractDao
 * @param <T> type of object persistence
 */
public interface IGenericDao<T, PK extends Serializable> {

    /**
     * Retrieves an entity by its id.
     * @param id - must not be null.
     * @return the entity with the given id or null if none found
     * @throws DaoException
     */
    T get(PK id) throws DaoException;

    /**
     * Returns all instances of the type.
     * @return all entities
     * @throws DaoException
     */
    List<T> getAll() throws DaoException;

    /**
     * Persist the given transient instance, first assigning a generated identifier.
     * (Or using the current value of the identifier property if the assigned generator is used.)
     * This operation cascades to associated instances if the association is mapped with cascade="save-update"
     * @param object - a transient instance of a persistent class
     * @return the generated identifier
     * @throws DaoException
     */
    PK add(T object) throws DaoException;

    /**
     * Either save(Object) or update(Object) the given instance, depending upon resolution of the unsaved-value checks.
     * This operation cascades to associated instances if the association is mapped with cascade="save-update"
     * @param object - a transient or detached instance containing new or updated state
     * @throws DaoException
     */
    void update(T object) throws DaoException;

    /**
     * Deletes a given object.
     * @param object - must not be null
     * @throws DaoException
     */
    void delete(T object) throws DaoException;

    /**
     * Create a Query instance for the given HQL query string.
     * @param hql - The HQL query
     * @return The query instance for manipulation and execution
     * @throws DaoException
     */
    Query getQuery(String hql) throws DaoException;
}
