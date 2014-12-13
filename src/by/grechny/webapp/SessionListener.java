package by.grechny.webapp;

import by.grechny.webapp.dao.GenericDAO;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {

        HttpSession session = se.getSession();
        Boolean isCreatedSession = (Boolean) session.getAttribute("isCreatedSession");
        if (isCreatedSession == null) {
            isCreatedSession = false;
        }

        if (isCreatedSession) {
            GenericDAO studentDAO = (GenericDAO) session.getAttribute("studentDAO");
            GenericDAO subjectDAO = (GenericDAO) session.getAttribute("subjectDAO");
            GenericDAO markDAO = (GenericDAO) session.getAttribute("markDAO");

            studentDAO.closeConnection();
            subjectDAO.closeConnection();
            markDAO.closeConnection();
        }

      /* Session is destroyed. */
    }
}
