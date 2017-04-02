package com.epam.jmp.jdbc.creators;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ales on 02.04.2017.
 */
public class PostsTableCreator {

    final static Logger logger = Logger.getLogger(PostsTableCreator.class);

    private final static String CREATE_POSTS_TABLE_SQL = "CREATE TABLE POSTS(ID NUMBER(20) NOT NULL, USER_ID NUMBER(20) NOT NULL, " +
            "TEXT VARCHAR2(50) NOT NULL, " +
            "TIMESTAMP DATE NOT NULL, FOREIGN KEY (USER_ID) REFERENCES USERS(ID), PRIMARY KEY (ID))";
    private static final String CREATE_SEQUENCE = "CREATE SEQUENCE AUTO_INCREMENT_POSTS START WITH 1";
    private static final String CREATE_TRIGGER = "create or replace TRIGGER POSTS_ADD_TRIGGER BEFORE INSERT ON POSTS FOR EACH ROW " +
            "BEGIN SELECT AUTO_INCREMENT_POSTS.NEXTVAL INTO :new.ID FROM dual; END;";


    public void createPostsTable(Connection connection) {

        try {
            PreparedStatement createPostsTable = connection.prepareStatement(CREATE_POSTS_TABLE_SQL);
            createPostsTable.execute();
            PreparedStatement createPostsSequesnce = connection.prepareStatement(CREATE_SEQUENCE);
            createPostsSequesnce.execute();
            Statement createPostsTrigger = connection.createStatement();
            createPostsTrigger.execute(CREATE_TRIGGER);
        } catch (SQLException e) {
            logger.error("SQL error while create POSTS table", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Can Not close connection", e);
            }
        }
    }


}
