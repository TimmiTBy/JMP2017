package com.epam.jmp.jdbc.creators;

import com.epam.jmp.jdbc.config.Connectable;
import com.epam.jmp.jdbc.entity.User;
import com.epam.jmp.jdbc.helper.DataHelper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ales on 02.04.2017.
 */
public class FriendshipsCreator {

    final static Logger logger = Logger.getLogger(UserTableCreator.class);

    private final static String CHECK_FIRENDSHIP_EXIST = "SELECT COUNT(*) AS TOTAL FROM FRIENDSHIP WHERE (USER1_ID=? AND USER2_ID=?)";

    private final static String CREATE_FRIENDSHIP = "INSERT INTO FRIENDSHIP (USER1_ID, USER2_ID, TIMESTAMP) " +
            "VALUES (?, ?, TO_DATE(?, 'DD.MM.YYYY'))";

    public void createFriendship(Connection connection) {
        for (int i = 1; i < 700000; i++) {
            User user1 = DataHelper.findUserById(connection);
            User user2 = DataHelper.findUserById(connection);
            if (user1.getId() != user2.getId()) {
                boolean friendshipExist = checkIfFriendshipExist(connection, user1.getId(), user2.getId());
                if (!friendshipExist) {
                    try {
                        PreparedStatement createFriendShipStatement = connection.prepareStatement(CREATE_FRIENDSHIP);
                        createFriendShipStatement.setInt(1, user1.getId());
                        createFriendShipStatement.setInt(2, user2.getId());
                        createFriendShipStatement.setString(3, startDateFriendship(user1, user2));
                        createFriendShipStatement.addBatch();
                        if (i % 100 == 0) {
                            createFriendShipStatement.executeBatch();
                        }
                    } catch (SQLException e) {
                        logger.error("Error while create Friendship", e);
                    }
                }
            }
            if (i % 500 == 0) {
                try {
                    connection.close();
                    connection = Connectable.getConnection();
                } catch (SQLException e) {
                    logger.error("Error while closing connection");
                }
            }
        }
    }

    private boolean checkIfFriendshipExist(Connection connection, int user1_id, int user2_id) {

        try {
            PreparedStatement findFriendShipStatement = connection.prepareStatement(CHECK_FIRENDSHIP_EXIST);
            findFriendShipStatement.setInt(1, user1_id);
            findFriendShipStatement.setInt(2, user2_id);
            ResultSet rs = findFriendShipStatement.executeQuery();
            while (rs.next()) {
                String total = rs.getString("TOTAL");
                if (Integer.parseInt(total) > 0) {
                    return true;
                }
            }
            rs.close();
            findFriendShipStatement.close();
        } catch (SQLException e) {
            logger.error("Error while check if freindship exist");
        }
        return false;
    }

    private String startDateFriendship(User user1, User user2) {
        Date lastBirthday = user1.getBirthday().getTime() > user2.getBirthday().getTime() ? user1.getBirthday() : user2.getBirthday();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastBirthday);
        calendar.add(Calendar.DATE, 500);
        Date startDateFriendship = calendar.getTime();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String formateDate = df.format(startDateFriendship);
        return formateDate;
    }


}
