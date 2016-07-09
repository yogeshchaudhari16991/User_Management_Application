package util;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import model.User;

/**
 * Created by yoges on 7/8/2016.
 */
public class UserAdapter {
    public static final DBObject toDBObject(User user) {
        //Address addrress = user.getAddress();
        return new BasicDBObject("_id", user.getId())
                .append("firstName", user.getFirstName())
                .append("lastName", user.getLastname())
                .append("email", user.getEmail())
                .append("address", new BasicDBObject("street", user.getAddress().getStreet())
                                    .append("city", user.getAddress().getCity())
                                    .append("zip", user.getAddress().getZip())
                                    .append("state", user.getAddress().getState())
                                    .append("country", user.getAddress().getCountry()))
                .append("dateCreated", user.getDateCreated())
                .append("company", new BasicDBObject("name", user.getCompany().getName())
                                    .append("website", user.getCompany().getWebsite()))

                .append("profilePic", user.getProfilePic());
    }
}
