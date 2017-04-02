package com.epam.jmp.jdbc.creators;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ales on 02.04.2017.
 */
public class UserTableCreator {

    final static Logger logger = Logger.getLogger(UserTableCreator.class);

    private static final String CREATE_USERS_TABLE = "CREATE TABLE USERS(ID NUMBER(20) NOT NULL, FIRST_NAME VARCHAR(20) NOT NULL, SURNAME VARCHAR(20) NOT NULL," +
            "BIRTHDAY DATE  NOT NULL, PRIMARY KEY (ID))";
    private static final String CREATE_SEQUENCE = "CREATE SEQUENCE AUTO_INCREMENT START WITH 1";
    private static final String CREATE_TRIGGER = "create or replace TRIGGER USER_ADD_TRIGGER BEFORE INSERT ON users FOR EACH ROW " +
            "BEGIN SELECT AUTO_INCREMENT.NEXTVAL INTO :new.ID FROM dual; END;";

    public void createUserTable(Connection connection) {

        try {
            PreparedStatement createUserTable = connection.prepareStatement(CREATE_USERS_TABLE);
            createUserTable.execute();
            PreparedStatement createUserSequence = connection.prepareStatement(CREATE_SEQUENCE);
            createUserSequence.execute();
            Statement createUserTrigger = connection.createStatement();
            createUserTrigger.execute(CREATE_TRIGGER);
        } catch (SQLException e) {
            logger.error("SQL error while create USERS table", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Can Not close connection", e);
            }
        }


    }
}

