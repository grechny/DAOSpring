package by.grechny.webapp.dao.mysql;

import by.grechny.webapp.dao.DAOFactory;
import by.grechny.webapp.dao.GenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class MySQLDAOFactory extends DAOFactory {

    public static EntityManager createConnection () {
        return Persistence.createEntityManagerFactory("MYSQL").createEntityManager();
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
