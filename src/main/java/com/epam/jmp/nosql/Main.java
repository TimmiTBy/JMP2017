package com.epam.jmp.nosql;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Ales on 25.06.2017.
 */
public class Main {

    static String names[] = {"Ales", "Pavel", "Nastia", "Maksim", "Ann", "Ira", "Andrew", "Ura", "Alla", "Tania"};

    static String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient();
        DB db = mongoClient.getDB("local");
        DBCollection collection = db.getCollection("friends");

        for (int i = 0; i < 10; i++) {
            BasicDBObject document = new BasicDBObject();
            document.append("name", names[ThreadLocalRandom.current().nextInt(0, 9)]);
            document.append("messages", getMessages());
            document.append("watchedMoveies", ThreadLocalRandom.current().nextInt(0, 1000));
            document.append("audioTracks", ThreadLocalRandom.current().nextInt(0, 500));
            document.append("friends", createFriendsList());
            collection.insert(document);
        }

        //Average messages
        BasicDBObject averageMessages = new BasicDBObject("$group", new BasicDBObject("_id", new ObjectId())
                .append("Monday", new BasicDBObject("$avg", "$messages.Monday"))
                .append("Tuesday", new BasicDBObject("$avg", "$messages.Tuesday"))
                .append("Wednesday", new BasicDBObject("$avg", "$messages.Wednesday"))
                .append("Thursday", new BasicDBObject("$avg", "$messages.Thursday"))
                .append("Friday", new BasicDBObject("$avg", "$messages.Friday"))
                .append("Saturday", new BasicDBObject("$avg", "$messages.Saturday"))
                .append("Sunday", new BasicDBObject("$avg", "$messages.Sunday"))
        );
        AggregationOutput messages = collection.aggregate(averageMessages);
        System.out.println("Average messages: " + messages.results());

        //Max of friends
        BasicDBObject numOfFriends = new BasicDBObject("$project", new BasicDBObject()
                .append("name", "$name")
                .append("numOfFriends", new BasicDBObject("$size","$friends"))
        );
        BasicDBObject sortByNumFriends = new BasicDBObject("$sort", new BasicDBObject()
                .append("numOfFriends", -1)
        );
        BasicDBObject limit = new BasicDBObject("$limit", 1);
        AggregationOutput maxNumOffriends= collection.aggregate(Arrays.asList(numOfFriends,sortByNumFriends, limit));
        System.out.println(maxNumOffriends.results());

        //Min num of films if friends more then 2
        BasicDBObject friendsMoreThenTwo = new BasicDBObject("$match",
                new BasicDBObject("friends.2", new BasicDBObject( "$exists", "true")));
        BasicDBObject minMovies = new BasicDBObject("$group", new BasicDBObject("_id", new ObjectId()).
                append("minMoveies", new BasicDBObject("$min", "$watchedMoveies")));
        AggregationOutput minNumOfFilms= collection.aggregate(Arrays.asList(friendsMoreThenTwo, minMovies));
        System.out.println(minNumOfFilms.results());
    }

    private static String[] createFriendsList() {
        List<String> friends = new ArrayList<String>();
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 9); i++) {
            friends.add(names[ThreadLocalRandom.current().nextInt(0, 9)]);
        }
        String[] friendsArray = {};
        return friends.toArray(friendsArray);
    }

    private static Document getMessages() {
        Document document = new Document();
        for (int i = 0; i < days.length; i++) {
            document.append(days[i], ThreadLocalRandom.current().nextInt(0, 300));
        }
        return document;
    }

}


