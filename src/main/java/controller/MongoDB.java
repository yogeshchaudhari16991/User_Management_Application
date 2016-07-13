package controller;

//////////////////////////////////////////////////////////////////////////////
// MongoDB.java - MongoDB defines methods to retrieve DB                    //
// Ver 1.0                                                                  //
// Application: User Management Application                                 //
// Language:    Java, ver 8, IntelliJ IDEA 2016.1.3                         //
// Platform:    Dell Inspiron 14 5000Series, Core-i5, Windows 10            //
// Author:      Yogesh Chaudhari, Intern, Syracuse University               //
//              (315) 4809210, yogeshchaudhari16991@gmail.com               //
//////////////////////////////////////////////////////////////////////////////
/*
 * File Operations:
 * -------------------
 *
 * This File implements DBInterface
 * and provides methods to retrieve Mongo database and collection objects
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *
 *      Java MongoDB driver
 *      java.net
 *      controller.interface.DBInterface
 *
 *
 * Build Process:
 *      DevEnv : mvn install
 *      DepEnv : mvn clean deploy
 *
 * Reference:   Java MongoDB Driver Documentation : https://docs.mongodb.com/ecosystem/drivers/java/
 * ----------
 *
 *
 * Maintenance History:
 * --------------------
 * ver 1.0 : 10 Jul 2016
 * - first release
 * ver 1.0.1 : 11 Jul 2016
 * - Interfaces and Factories added
 *
 */


//imports
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import controller.interfaces.DBInterface;
import java.net.UnknownHostException;

// MongoDb class implementing BDInterface
public class MongoDB implements DBInterface<DB, DBCollection> {
    // private Mongo DB client for app
    private static MongoClient mongoClient;

    public MongoDB() { }

    @Override
    //get mongoDB database object
    public DB getDB(String databaseName) {
        try {
            mongoClient = new MongoClient();
            DB database = mongoClient.getDB(databaseName);
            return database;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            mongoClient.close();
            return null;
        }
    }

    @Override
    // get Mongo DB Collection object
    public DBCollection getDB(String databaseName, String collectionName) {
        try {
            mongoClient = new MongoClient();
            DB database = mongoClient.getDB(databaseName);
            DBCollection collection = database.getCollection(collectionName);
            return collection;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            mongoClient.close();
            return null;
        }
    }
}
