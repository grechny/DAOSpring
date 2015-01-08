package by.grechny.webapp;

import by.grechny.webapp.dao.DAOFactory;
import by.grechny.webapp.dao.GenericDAO;
import by.grechny.webapp.dao.mysql.MySQLDAOFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        HttpSession session = se.getSession();

        DAOFactory mysqlFactory;
        try {
            mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        GenericDAO studentDAO = mysqlFactory.getStudentsDAO();
        GenericDAO subjectDAO = mysqlFactory.getSubjectsDAO();
        GenericDAO markDAO = mysqlFactory.getMarksDAO();

        session.setAttribute("studentDAO",studentDAO);
        session.setAttribute("subjectDAO",subjectDAO);
        session.setAttribute("markDAO",markDAO);
    }

    public void sessionDestroyed(HttpSessionEvent se) {

        MySQLDAOFactory.closeConnection();
        /* Session is destroyed. */
    }
}
