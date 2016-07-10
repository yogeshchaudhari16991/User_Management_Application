/**
 * Created by yogesh on 7/7/2016.
 */

// imports
import com.mongodb.*;
import controller.UserController;
import model.UserEngine;

public class App {
    // private Mongo DB client for app
    private static MongoClient mongoClient;

    // entry method for app - Main method
    public static void main(String[] args) {
        try {
            new UserController(new UserEngine(mongo()));
        } catch (Exception e) {
            e.printStackTrace();
            mongoClient.close();
        }
    }

    // get Mongo DB Collection object
    private static DBCollection mongo() throws Exception {
        mongoClient = new MongoClient();
        DB database = mongoClient.getDB("UserManagementSystem");
        DBCollection collection = database.getCollection("Users");
        collection.remove(new BasicDBObject());
        return collection;
    }
}
