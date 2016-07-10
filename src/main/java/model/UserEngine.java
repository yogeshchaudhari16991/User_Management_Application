package model;

// imports
import java.util.ArrayList;
import java.util.List;
import com.mongodb.*;
// static imports
import static util.JsonUtil.fromJson;
import static util.UserEngineExtensions.*;

/**
 * Created by yoges on 7/8/2016.
 */
public class UserEngine {

    // encapsulated fields
    private List<User> users = new ArrayList<User>();
    private DBCollection mongo;

    // Public Constructor
    public UserEngine(DBCollection mongo) {
        this.mongo = mongo;
    }

    // CRUD methods for User
    public boolean insertUser(String json){
        User user = (User) fromJson(json);
        if(user.getId() == null || user.getId().isEmpty()){
            return false;
        }
        try {
            this.mongo.insert(toDBObject(user), WriteConcern.SAFE);
            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred while inserting Object: " + e );
            return false;
        }
    }

    public List<User> getAllUsers(){
        DBCursor cursor = this.mongo.find();
        // clear previous data
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
        if(user != null) {
            return user;
        }
        return null;
    }

    public boolean updateUser(String id, String jsonBody){
        DBCursor cursor = this.mongo.find(new BasicDBObject("_id", id));
        if(cursor.size() == 0){
            return false;
        }
        try {
            DBObject curr = cursor.next();
            User user = (User)fromJson(jsonBody);
            BasicDBObject newObj = (BasicDBObject) updateFileds(user, curr);
            // flags for upsert=false, multi=false
            this.mongo.update(new BasicDBObject("_id", id), newObj, false, false);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    // Delete after Development phase
    public boolean demoData(){
        for(int i=0; i<5; i++){
            User user = new User("100"+i, "firstName", "lastName", "email", "dateCreated", "street", "city", "zip", "state", "country", "profilePic", "companyName", "website");
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
