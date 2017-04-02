package com.epam.jmp.jdbc.config;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Ales on 02.04.2017.
 */
public class Connectable {

    final static Logger logger = Logger.getLogger(Connectable.class);

    public static Connection getConnection() {

        Properties dbProperties = new Properties();
        InputStream stream = null;
        Connection connection = null;
        try {
            stream = new FileInputStream("src/main/resources/database.properties");
            dbProperties.load(stream);
            connection = DriverManager.getConnection(dbProperties.getProperty("jdbc.url"), dbProperties.getProperty("jdbc.username"),
                    dbProperties.getProperty("jdbc.password"));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
