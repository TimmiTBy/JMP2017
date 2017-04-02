package com.epam.jmp.jdbc.creators;

import com.epam.jmp.jdbc.helper.DataHelper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ales on 02.04.2017.
 */
public class LikesCreator {

    final static Logger logger = Logger.getLogger(UserTableCreator.class);

    private static final String CHECK_IF_POST_HAVE_LIKE_FROM_CURRENT_USER = "SELECT COUNT(*) AS TOTAL FROM LIKES WHERE USER_ID=? and POST_ID=?";
    private static final String CREATE_LIKE = "INSERT INTO LIKES (POST_ID, USER_ID, TIMESTAMP) " +
            "VALUES (?, ?, TO_DATE(?, 'DD.MM.YYYY'))";

    public void createLikes(Connection connection) {
        try {
            PreparedStatement likesInsertStatement = connection.prepareStatement(CREATE_LIKE);
            for (int i = 0; i < 3001; i++) {
                int userId = DataHelper.generateRandomUserId();
                int postId = DataHelper.generateRandomPostId();
                boolean haveLike = ifPostHaveLikeOfCurrentUser(userId, postId, connection);
                if(!haveLike) {
                    likesInsertStatement.setInt(1, postId);
                    likesInsertStatement.setInt(2, userId);
                    likesInsertStatement.setString(3, DataHelper.generateRandomLikeDate());
                    likesInsertStatement.addBatch();
                    if(i % 100 == 0) {
                        likesInsertStatement.executeBatch();
                    }
                }
            }
            likesInsertStatement.close();
        } catch (SQLException e) {
            logger.error("Error while add new Like to DB", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Error while close connection", e);
            }
        }
    }

    private boolean ifPostHaveLikeOfCurrentUser(int userId,  int postId, Connection connection) {
        try {
            PreparedStatement checkLikeInsert = connection.prepareStatement(CHECK_IF_POST_HAVE_LIKE_FROM_CURRENT_USER);
            checkLikeInsert.setInt(1, userId);
            checkLikeInsert.setInt(2, postId);
            ResultSet rs = checkLikeInsert.executeQuery();
            while (rs.next()) {
                String total = rs.getString("TOTAL");
                if (Integer.parseInt(total) > 0) {
                    return true;
                }
            }
            rs.close();
            checkLikeInsert.close();
        } catch (SQLException e) {
            logger.error("Error while checking likes in DB");
        }
        return false;

    }
}
