package by.grechny.webapp.dao.mysql;

import by.grechny.webapp.dao.DAOFactory;
import by.grechny.webapp.dao.GenericDAO;

import javax.persistence.EntityManager;

public class MySQLDAOFactory extends DAOFactory {

    private static EntityManager entityManager;

    public MySQLDAOFactory(EntityManager entityManager) {
        MySQLDAOFactory.entityManager = entityManager;
    }

    public static EntityManager getConnection() {
        return MySQLDAOFactory.entityManager;
    }

    public static void closeConnection() {
        MySQLDAOFactory.entityManager.close();
    }

    @Override
    public GenericDAO getStudentsDAO() {
        return new StudentsMySQLDAO();
    }

    @Override
    public GenericDAO getSubjectsDAO() {
        return new SubjectsMySQLDAO();
    }

    @Override
    public GenericDAO getMarksDAO() {
        return new MarksMySQLDAO();
    }


}
