package com.epam.jmp.jdbc.creators;

import com.epam.jmp.jdbc.entity.User;
import com.epam.jmp.jdbc.helper.DataHelper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ales on 02.04.2017.
 */
public class PostsCreator {

    final static Logger logger = Logger.getLogger(PostsCreator.class);

    public void createPosts(Connection connection) {
        try {
            PreparedStatement postsInsertStatement = connection.prepareStatement("INSERT INTO POSTS (USER_ID, TEXT, TIMESTAMP) " +
                    "VALUES (?, ?, TO_DATE(?, 'DD.MM.YYYY'))");
            for (int i = 0; i < 3001; i++) {
                User user = DataHelper.findUserById(connection);
                postsInsertStatement.setInt(1,user.getId());
                postsInsertStatement.setString(2, "aaaaaa aaaaaa aaaaa nnnnn nnnnn");
                postsInsertStatement.setString(3, createPostDate(user));
                postsInsertStatement.addBatch();
                if (i % 100 == 0 ) {
                    postsInsertStatement.executeBatch();
                }
            }
            postsInsertStatement.close();
        } catch (SQLException e) {
            logger.error("Error while add new users to DB", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Error while close connection", e);
            }
        }
    }

    private String createPostDate(User user) {
        Date birthday = user.getBirthday();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        calendar.add(Calendar.DATE, 1000);
        Date postDate = calendar.getTime();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String formateDate = df.format(postDate);
        return formateDate;
    }
}
