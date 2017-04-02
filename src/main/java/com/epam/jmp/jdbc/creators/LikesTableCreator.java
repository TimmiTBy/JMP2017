package com.epam.jmp.jdbc.creators;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ales on 02.04.2017.
 */
public class LikesTableCreator {

    final static Logger logger = Logger.getLogger(PostsCreator.class);

    private final static String CREATE_LIKES_TABLE_SQL = "CREATE TABLE LIKES(POST_ID NUMBER(20) NOT NULL, USER_ID NUMBER(20) NOT NULL, " +
            "TIMESTAMP DATE NOT NULL, FOREIGN KEY (USER_ID) REFERENCES USERS(ID), FOREIGN KEY (POST_ID) REFERENCES POSTS(ID))";

    public void createLikesTable(Connection connection) {
        try {
            PreparedStatement createLikesTable = connection.prepareStatement(CREATE_LIKES_TABLE_SQL);
            createLikesTable.execute();
        } catch (SQLException e) {
            logger.error("SQL error while create LIKES table", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Can Not close connection", e);
            }
        }
    }
}
