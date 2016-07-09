package model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.*;
import org.bson.types.ObjectId;

import static util.UserAdapter.toDBObject;

/**
 * Created by yoges on 7/8/2016.
 */
public class UserEngine {

    private List<User> users = new ArrayList<User>();
    private DBCollection mongo;

    public UserEngine(DBCollection mongo) {
        this.mongo = mongo;
    }


    //public UserEngine()

    public boolean createUser(String id, String firstName, String lastName, String email, String dateCreated, String street, String city,
                              String zip, String state, String country, String profilePic, String companyName, String website){
        // Add Id check for user with same ID already present or not
        User user = new User(id, firstName, lastName, email, dateCreated, street, city, zip, state, country, profilePic, companyName, website);

        try{
            this.mongo.insert(toDBObject(user), WriteConcern.SAFE);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public List<User> getAllUsers(){

        DBCursor cursor = this.mongo.find();
        for(DBObject dbObj : cursor){
            users.add(new User((String)dbObj.get("_id"), (String)dbObj.get("firstName"), (String)dbObj.get("lastName"),
                    (String)dbObj.get("email"), (String)dbObj.get("dateCreated"), (String) dbObj.get("address.street"),
                    (String)dbObj.get("address.city"), (String)dbObj.get("address.zip"), (String)dbObj.get("address.state"),
                    (String)dbObj.get("address.country"), (String)dbObj.get("profilePic"), (String)dbObj.get("company.name"),
                    (String)dbObj.get("company.website")));
        }
        return users;
    }

    public User getUser(String id){
        //this.mongo.find()
        User user = new User();
        // Add logic to find user based on id from mongoDB
        return user;
    }

    public boolean updateUser(){
        // Add code to update User
        return true;
    }

    public boolean demoData(){
        for(int i=0; i<5; i++){
            User user = new User("100"+i, "firstName", "lastName", "email", "dateCreated", "street", "city", "zip", "state", "country", "profilePic", "companyName", "website");
            try{
                this.mongo.insert(toDBObject(user), WriteConcern.SAFE);
                //return true;
            }
            catch(Exception e){
                return false;
            }
        }
        return true;
    }
}
