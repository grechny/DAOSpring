package by.grechny.webapp.dao;

import by.grechny.webapp.dao.mysql.MySQLDAOFactory;

public abstract class DAOFactory {

    public static enum Factory { MYSQL }

    public abstract GenericDAO getStudentsDAO();
    public abstract GenericDAO getSubjectsDAO();
    public abstract GenericDAO getMarksDAO();

    public static DAOFactory getDAOFactory(Factory whichFactory) throws Exception {

        switch (whichFactory) {
            case MYSQL:
                return new MySQLDAOFactory();
            default:
                throw new Exception("Create DAO Factory error");
        }
    }
}
