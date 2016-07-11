

///////////////////////////////////////////////////////////////////////////////////
// App.java - Starting Point for Application - Defines Entry point Main() method //
// Ver 1.0                                                                       //
// Application: User Management Application                                      //
// Language:    Java, ver 8, IntelliJ IDEA 2016.1.3                              //
// Platform:    Dell Inspiron 14 5000Series, Core-i5, Windows 10                 //
// Author:      Yogesh Chaudhari, Intern, Syracuse University                    //
//              (315) 4809210, yogeshchaudhari16991@gmail.com                    //
///////////////////////////////////////////////////////////////////////////////////
/*
 * File Operations:
 * -------------------
 *
 * This file implements main() method - entry point for Application
 * Using DBCollection method starts MongoDB client and gets Collection data - DB
 * In main method passes retrieved collection object to new UserEngine object
 * Passes UserEngine object to new UserController object
 * By creating UserController object starts Spark application
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *      Java MongoDB driver
 *      model.UserEngine.java
 *      controller.UserController.java
 *
 * Build Process:
 *      DevEnv : mvn install
 *      DepEnv : mvn clean deploy
 *
 * Reference:   Java MongoDB Driver Documentation : https://docs.mongodb.com/ecosystem/drivers/java/
 * ----------   Building Simple REST API with Java Spark : https://dzone.com/articles/building-simple-restful-api
 *
 *
 * Maintenance History:
 * --------------------
 * ver 1.0 : 10 Jul 2016
 * - first release
 *
 */


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
        // delete previous data
        // Uncomment when in Testing mode
        collection.remove(new BasicDBObject());
        return collection;
    }
}
