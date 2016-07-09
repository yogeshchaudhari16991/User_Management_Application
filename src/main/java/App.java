/**
 * Created by yogesh on 7/7/2016.
 */

import com.mongodb.*;

import controller.UserController;
import model.UserEngine;

public class App {
    static MongoClient mongoClient;
    public static void main(String[] args) {
            try {
                new UserController(new UserEngine(mongo()));
            }
            catch (Exception e) {
                System.out.println("Exception Occurred: "+ e);
                mongoClient.close();
            }
        }

        private static DBCollection mongo() throws Exception {
            mongoClient = new MongoClient();
            DB database = mongoClient.getDB("UserManagementSystem");
            DBCollection collection = database.getCollection("Users");
            return collection;
    }
}
