package com.epam.jmp.jdbc.creators;

import com.epam.jmp.jdbc.helper.DataHelper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Ales on 02.04.2017.
 */
public class UsersCreator {

    final static Logger logger = Logger.getLogger(UsersCreator.class);

    public void createUsers(Connection connection) {
        try {
            PreparedStatement userInsertStatement = connection.prepareStatement("INSERT INTO USERS (FIRST_NAME, SURNAME, BIRTHDAY) " +
                    "VALUES (?, ?, TO_DATE(?, 'DD.MM.YYYY'))");
            for (int i = 0; i < 1001; i++) {
                userInsertStatement.setString(1, generateRandomFirstName());
                userInsertStatement.setString(2, generateRandomSurname());
                userInsertStatement.setString(3, generateUserRandomDate());
                userInsertStatement.addBatch();
                if (i % 100 == 0 ) {
                    userInsertStatement.executeBatch();
                }
            }

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

    private String generateRandomFirstName() {
        List<String> firstNames = DataHelper.getFirstNames();
        int randomFirstNameIndex = ThreadLocalRandom.current().nextInt(0, 39);
        String randomUserFirstName = firstNames.get(randomFirstNameIndex);
        return randomUserFirstName;
    }

    private String generateRandomSurname() {
        List<String> surnames = DataHelper.getSurname();
        int randomFirstNameIndex = ThreadLocalRandom.current().nextInt(0, 39);
        String randomUserSurname = surnames.get(randomFirstNameIndex);
        return randomUserSurname;
    }

    private String generateUserRandomDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = randomDate.format(formatter);
        return date;
    }
}

