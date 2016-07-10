package model;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.*;

import static util.JsonUtil.fromJson;
import static util.UserEngineExtensions.*;

/**
 * Created by yoges on 7/8/2016.
 */
public class UserEngine {

    private List<User> users = new ArrayList<User>();
    private DBCollection mongo;

    public UserEngine(DBCollection mongo) {
        this.mongo = mongo;
    }

    public boolean insertUser(String json){
        User user = (User) fromJson(json);
        if(user.getId() == null || user.getId().isEmpty()){
            return false;
        }
        System.out.println(user);
        try {
            this.mongo.insert(toDBObject(user), WriteConcern.SAFE);
            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e );
            return false;
        }
    }

    public List<User> getAllUsers(){

        DBCursor cursor = this.mongo.find();
        System.out.println("UserEngine - Cursor Size:" + cursor.size());
        users.clear();
        for(DBObject dbObj : cursor){
            users.add(toUserObject(dbObj));
        }
        return users;
    }

    public User getUser(String id){
        DBCursor cursor = this.mongo.find(new BasicDBObject("_id", id));
        if(cursor.size() == 0) {
            return null;
        }
        User user = toUserObject(cursor.next());
        if(user != null)
            return user;
        return null;
    }

    public boolean updateUser(String id, String jsonBody){
        // Add code to update User
        DBCursor cursor = this.mongo.find(new BasicDBObject("_id", id));
        if(cursor.size() == 0){
            return false;
        }
        try {
            DBObject curr = cursor.next();
            User user = (User)fromJson(jsonBody);
            System.out.println("In UserEngine - " + user);
            BasicDBObject newObj = (BasicDBObject) updateFileds(user, curr);
            // upsert=false, multi=false
            this.mongo.update(new BasicDBObject("_id", id), newObj, false, false);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean demoData(){
        for(int i=0; i<5; i++){
            User user = createNewUser("100"+i, "firstName", "lastName", "email", "dateCreated", "street", "city", "zip", "state", "country", "profilePic", "companyName", "website");
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
