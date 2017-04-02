package com.epam.jmp.jdbc.creators;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ales on 02.04.2017.
 */
public class FriendshipsTableCreator {

    final static Logger logger = Logger.getLogger(UserTableCreator.class);

    private final static String CREATE_FRIENDSHIPS_TABLE_SQL = "CREATE TABLE FRIENDSHIP(USER1_ID NUMBER(20) NOT NULL, USER2_ID NUMBER(20) NOT NULL, " +
            "TIMESTAMP DATE NOT NULL, FOREIGN KEY (USER1_ID) REFERENCES USERS(ID), " +
            "FOREIGN KEY (USER2_ID) REFERENCES USERS(ID))";

    public void createFriendshipTable(Connection connection) {

        try {
            PreparedStatement createUserTable = connection.prepareStatement(CREATE_FRIENDSHIPS_TABLE_SQL);
            createUserTable.execute();
        } catch (SQLException e) {
            logger.error("SQL error while create FRIENDSHIP table", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Can Not close connection", e);
            }
        }
    }
}
