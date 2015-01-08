package by.grechny.webapp.dao.mysql;

import by.grechny.webapp.dao.GenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractMySQLDAO<T> implements GenericDAO<T> {

    protected EntityManager em;

    public AbstractMySQLDAO() {
        this.em = MySQLDAOFactory.getConnection();
    }

    public abstract String selectAllQuery();

    @Override
    public List<T> selectAll() throws SQLException {
        em.getTransaction().begin();
        String selectAllQuery = selectAllQuery();
        TypedQuery<T> query = (TypedQuery<T>) em.createQuery(selectAllQuery);
        List<T> list = query.getResultList();
        em.getTransaction().commit();
        return list;
    }

    @Override
    public T create(T object) throws SQLException {
        em.getTransaction().begin();
        T objectFromDB = em.merge(object);
        em.getTransaction().commit();
        return objectFromDB;
    }

    @Override
    public void update(T object) throws SQLException {
        em.getTransaction().begin();
        em.merge(object);
        em.getTransaction().commit();
    }

    @Override
    public void delete(T object) throws SQLException {
        em.getTransaction().begin();
        em.remove(em.merge(object));
        em.getTransaction().commit();
    }
/*
    @Override
    public void closeConnection() {
        em.close();
    }*/
}