package controller;


//////////////////////////////////////////////////////////////////////////////
// UserEngine.java - UserEngine for MongoDB                   //
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
 * This File implements UserEngineInterface
 * Uses Util files and Java Mongo driver to perform CRUD operations on MongoDB and JSON format data
 *
 *
 * Note: DemoData() function, generates pre-processing data
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *      model.User.java;
 *      util.ErrorClass.java
 *      util.JsonUtil.java
 *      Google gson
 *      Java MongoDB driver
 *      java.util
 *
 *
 * Build Process:
 *      DevEnv : mvn install
 *      DepEnv : mvn clean deploy
 *
 * Reference:   Java MongoDB Driver Documentation : https://docs.mongodb.com/ecosystem/drivers/java/
 * ----------   Google Gson User-Guide : https://sites.google.com/site/gson/gson-user-guide
 *
 *
 * Maintenance History:
 * --------------------
 * ver 1.0 : 10 Jul 2016
 * - first release
 *
 */

// imports
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mongodb.*;
import interfaces.UserEngineInterface;
import model.User;
import util.EmailValidator;
// static imports
import static util.JsonUtil.fromJson;
import static util.UserEngineExtensions.*;

public class UserEngine implements UserEngineInterface {

    // encapsulated fields
    private final DBCollection mongo;

    // Public Constructor
    public UserEngine(DBCollection mongo, boolean isTest) {
        this.mongo = mongo;
        if(isTest){
            demoData();
        }
    }

    // CRUD methods for User

    //insert User to DB
    public boolean insertUser(String json){
        User user = (User) fromJson(json);
        if(user != null) {
            if (user.getId() == null) {
                return false;
            }
            try {
                DBCursor cursor = this.mongo.find(new BasicDBObject("_id", user.getId()));
                if (cursor.size() > 0) {
                    return false;
                }
                EmailValidator emailValidator = new EmailValidator();
                if(emailValidator.validate(user.getEmail())) {
                    this.mongo.insert(toDBObject(user), WriteConcern.SAFE);
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Exception occurred while inserting Object: " + e);
                return false;
            }
        }
        return false;
    }

    //retrieve all users from DB
    public List<User> getAllUsers(){
        DBCursor cursor = this.mongo.find();
        // create new List of Users
        if(cursor.size() > 0) {
            List<User> users = new ArrayList<User>(cursor.size());
            for (DBObject dbObj : cursor) {
                users.add(toUserObject(dbObj));
            }
            return users;
        } else {
            return null;
        }
    }

    //get user with specific ID from DB
    public User getUser(String id){
        try {
            DBCursor cursor = this.mongo.find(new BasicDBObject("_id", UUID.fromString(id)));
            if(cursor.size() == 0) {
                return null;
            }
            User user = toUserObject(cursor.next());
            if(user != null) {
                return user;
            }
            return null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    //update user information if user is already present in DB
    public boolean updateUser(String id, String jsonBody){
        if(id != null){
            try {
                UUID idUUID = UUID.fromString(id);
                DBCursor cursor = this.mongo.find(new BasicDBObject("_id", idUUID));
                if (cursor.size() == 0) {
                    return false;
                }
                try {
                    DBObject curr = cursor.next();
                    User user = (User) fromJson(jsonBody);
                    EmailValidator emailValidator = new EmailValidator();
                    if (emailValidator.validate(user.getEmail())) {
                        BasicDBObject newObj = (BasicDBObject) updateFileds(user, curr);
                        // flags for upsert=false, multi=false
                        this.mongo.update(new BasicDBObject("_id", idUUID), newObj, false, false);
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }


    // Only for Testing phase
    // generate pre-processing data
    public boolean demoData(){
        for(long i=0L; i<5; i++){
            User user = createNewUser(UUID.randomUUID(), "firstName", "lastName", "Darby_Leffler68@gmail.com", "2016-03-15T07:02:40.896Z", "street", "city", "zip", "state",
                    "country", "profilePic", "companyName", "website");
                try {
                    this.mongo.update(new BasicDBObject("_id", user.getId()),toDBObject(user), true, false);
                } catch (Exception e) {
                    System.out.println("In UserEngine: " + e);
                    e.printStackTrace();
                    return false;
                }
        }
        return true;
    }
}
