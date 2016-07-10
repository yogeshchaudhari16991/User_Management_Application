package util;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import model.Address;
import model.Company;
import model.User;

/**
 * Created by yoges on 7/8/2016.
 */
public class UserEngineExtensions {
    public static final DBObject toDBObject(User user) {
        BasicDBObject obj = new BasicDBObject("_id", user.getId());
        if(user.getFirstName() != null && (!user.getFirstName().isEmpty())){
            obj.append("firstName", user.getFirstName());
        }
        if(user.getLastname() != null && (!user.getLastname().isEmpty())){
            obj.append("lastName", user.getLastname());
        }
        if(user.getEmail() != null && (!user.getEmail().isEmpty())){
            obj.append("email", user.getEmail());
        }
        if(user.getAddress() != null) {
            if (addressValid(user.getAddress())) {
                BasicDBObject addObj = new BasicDBObject();
                if (user.getAddress().getStreet() != null && (!user.getAddress().getStreet().isEmpty())) {
                    addObj.append("street", user.getAddress().getStreet());
                }
                if (user.getAddress().getCity() != null && (!user.getAddress().getCity().isEmpty())) {
                    addObj.append("city", user.getAddress().getCity());
                }
                if (user.getAddress().getZip() != null && (!user.getAddress().getZip().isEmpty())) {
                    addObj.append("zip", user.getAddress().getZip());
                }
                if (user.getAddress().getState() != null && (!user.getAddress().getState().isEmpty())) {
                    addObj.append("state", user.getAddress().getState());
                }
                if (user.getAddress().getCountry() != null && (!user.getAddress().getCountry().isEmpty())) {
                    addObj.append("country", user.getAddress().getCountry());
                }
                obj.append("address", addObj);
            }
        }
        if(user.getDateCreated() != null && (!user.getDateCreated().isEmpty())){
            obj.append("dateCreated", user.getDateCreated());
        }
        if(user.getCompany()!= null) {
            if (companyValid(user.getCompany())) {
                BasicDBObject comObj = new BasicDBObject();
                if (user.getCompany().getName() != null && !user.getCompany().getName().isEmpty()) {
                    comObj.append("name", user.getCompany().getName());
                }
                if (user.getCompany().getWebsite() != null && !user.getCompany().getWebsite().isEmpty()) {
                    comObj.append("website", user.getCompany().getWebsite());
                }
                obj.append("company", comObj);
            }
        }
        if(user.getProfilePic() != null && (!user.getProfilePic().isEmpty())){
            obj.append("profilePic", user.getProfilePic());
        }
        return obj;
    }

    private static boolean companyValid(Company company) {
        return (company.getName()!= null || company.getWebsite() != null) && (!company.getName().isEmpty() || !company.getWebsite().isEmpty());
    }

    private static boolean addressValid(Address address) {
        return (address.getStreet() != null || address.getCity() != null || address.getZip() != null || address.getState() != null
        || address.getCountry() != null) && (!address.getStreet().isEmpty() || !address.getCity().isEmpty() || !address.getZip().isEmpty()
        || !address.getState().isEmpty() || !address.getCountry().isEmpty());
    }

    public static final User toUserObject(DBObject dbObj) {
        if(dbObj != null) {
            return new User((String) dbObj.get("_id"), (String) dbObj.get("firstName"), (String) dbObj.get("lastName"),
                    (String) dbObj.get("email"), (String) dbObj.get("dateCreated"), (String) dbObj.get("address.street"),
                    (String) dbObj.get("address.city"), (String) dbObj.get("address.zip"), (String) dbObj.get("address.state"),
                    (String) dbObj.get("address.country"), (String) dbObj.get("profilePic"), (String) dbObj.get("company.name"),
                    (String) dbObj.get("company.website"));

        }
        return null;
    }

    public static final User createNewUser(String id, String firstName, String lastName, String email, String dateCreated, String street, String city, String zip, String state, String country, String profilePic, String companyName, String website) {
        return new User(id, firstName, lastName, email, dateCreated, street, city, zip, state, country, profilePic, companyName, website);
    }

    public static final DBObject updateFileds(User user, DBObject obj) {
        if(user.getFirstName() != null && (!user.getFirstName().isEmpty())){
            obj.put("firstName", user.getFirstName());
        }
        if(user.getLastname() != null && (!user.getLastname().isEmpty())){
            obj.put("lastName", user.getLastname());
        }
        if(user.getEmail() != null && (!user.getEmail().isEmpty())){
            obj.put("email", user.getEmail());
        }
        if(addressValid(user.getAddress())){
            BasicDBObject addObj;
            if(obj.get("address") != null) {
                addObj = (BasicDBObject) obj.get("address");
            } else {
                addObj = new BasicDBObject();
            }
            if(user.getAddress().getStreet() != null && (!user.getAddress().getStreet().isEmpty())){
                addObj.put("street", user.getAddress().getStreet());
            }
            if(user.getAddress().getCity() != null && (!user.getAddress().getCity().isEmpty())){
                addObj.put("city", user.getAddress().getCity());
            }
            if(user.getAddress().getZip() != null && (!user.getAddress().getZip().isEmpty())){
                addObj.put("zip", user.getAddress().getZip());
            }
            if(user.getAddress().getState() != null && (!user.getAddress().getState().isEmpty())){
                addObj.put("state", user.getAddress().getState());
            }
            if(user.getAddress().getCountry() != null && (!user.getAddress().getCountry().isEmpty())){
                addObj.put("country", user.getAddress().getCountry());
            }
            obj.put("address", addObj);
        }
        if(user.getDateCreated() != null && (!user.getDateCreated().isEmpty())){
            obj.put("dateCreated", user.getDateCreated());
        }
        if(companyValid(user.getCompany())){
            BasicDBObject comObj;
            if(obj.get("company") != null) {
                comObj = (BasicDBObject) obj.get("company");
            } else {
                comObj = new BasicDBObject();
            }
            if(user.getCompany().getName() != null && !user.getCompany().getName().isEmpty()){
                comObj.put("name", user.getCompany().getName());
            }
            if(user.getCompany().getWebsite() != null && !user.getCompany().getWebsite().isEmpty()){
                comObj.put("website", user.getCompany().getWebsite());
            }
            obj.put("company", comObj);
        }
        if(user.getProfilePic() != null && (!user.getProfilePic().isEmpty())){
            obj.put("profilePic", user.getProfilePic());
        }
        return obj;
    }
}
