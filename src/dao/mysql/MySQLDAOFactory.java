package dao.mysql;

import dao.DAOFactory;
import dao.GenericDAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDAOFactory extends DAOFactory {

    public static Connection createConnection () {

        InputStream file;
        Properties properties = new Properties();

        try {
            file = MySQLDAOFactory.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(file);
        } catch (IOException e) {
            System.err.println("Properties file not found");
            return null;
        }

        String DRIVER = properties.getProperty("DRIVER");
        String DBURL = properties.getProperty("DBURL");
        String DBUSER = properties.getProperty("DBUSER");
        String DBPASS = properties.getProperty("DBPASS");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection c;
        try {
            c = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return c;
    }

    public GenericDAO getStudentsDAO() {
        return new StudentsMySQLDAO();
    }
    public GenericDAO getSubjectsDAO() {
        return new SubjectsMySQLDAO();
    }
    public GenericDAO getMarksDAO() {
        return new MarksMySQLDAO();
    }


}
