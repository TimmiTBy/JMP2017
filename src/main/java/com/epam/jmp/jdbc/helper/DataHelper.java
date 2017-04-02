package com.epam.jmp.jdbc.helper;

import com.epam.jmp.jdbc.entity.User;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Ales on 02.04.2017.
 */
public class DataHelper {

    final static Logger logger = Logger.getLogger(DataHelper.class);

    private final static String FIND_USER_BY_ID = "SELECT * FROM USERS WHERE ID=?";

    public static List<String> getFirstNames() {
        List<String> firstNamesList = new ArrayList<String>();
        try {
            InputStream in = new FileInputStream(new File("src/main/resources/firstnames.txt"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                firstNamesList.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstNamesList;
    }

    public static List<String> getSurname() {
        List<String> firstNamesList = new ArrayList<String>();
        try {
            InputStream in = new FileInputStream(new File("src/main/resources/lastnames.txt"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                firstNamesList.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstNamesList;
    }


    public static User findUserById(Connection connection) {
        User user = null;
        try {
            PreparedStatement findUserStatement = connection.prepareStatement(FIND_USER_BY_ID);
            findUserStatement.setInt(1, generateRandomUserId());
            ResultSet rs = findUserStatement.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("ID");
                Date birthday = rs.getDate("BIRTHDAY");
                user = new User(Integer.parseInt(userId), birthday);
            }
            rs.close();
            findUserStatement.close();
        } catch (SQLException e) {
            logger.error("Error while getting User", e);
        }
        return user;
    }

    public static int generateRandomUserId() {
        return ThreadLocalRandom.current().nextInt(1906, 2804);
    }

    public static int generateRandomPostId() {
        return ThreadLocalRandom.current().nextInt(1, 3001);
    }

    public static String generateRandomLikeDate() {
        long minDay = LocalDate.of(2016, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2018, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = randomDate.format(formatter);
        return date;
    }


}

