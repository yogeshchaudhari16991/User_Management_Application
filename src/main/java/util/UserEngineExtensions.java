package util;

// imports
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import model.Address;
import model.Company;
import model.User;

/**
 * Created by yoges on 7/8/2016.
 */
public class UserEngineExtensions {

    // Static Utility methods
    public static DBObject toDBObject(User user) {
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

    public static boolean companyValid(Company company) {
        return companyValid(company.getName(), company.getWebsite());
    }

    public static boolean companyValid(String name, String website) {
        return (name != null || website != null) && (!name.isEmpty() || !website.isEmpty());
    }

    public static boolean addressValid(Address address) {
        return addressValid(address.getStreet(), address.getCity(), address.getZip(), address.getState(), address.getCountry());
    }

    public static boolean addressValid(String street, String city, String zip, String state, String country) {
        return (street != null || city != null || zip != null || state != null || country != null) &&
                (!street.isEmpty() || !city.isEmpty() || !zip.isEmpty() || !state.isEmpty() || !country.isEmpty());
    }


    public static User toUserObject(DBObject dbObj) {
        if(dbObj != null) {
            User user;
            BasicDBObject addObj = null;
            if(dbObj.containsField("address")){
               addObj = (BasicDBObject) dbObj.get("address");
            }
            BasicDBObject comObj = null;
            if(dbObj.containsField("company")) {
                comObj = (BasicDBObject) dbObj.get("company");
            }
            if(addObj != null && comObj != null) {
                 user = new User((String) dbObj.get("_id"), (String) dbObj.get("firstName"), (String) dbObj.get("lastName"),
                        (String) dbObj.get("email"), (String) dbObj.get("dateCreated"), (String) addObj.get("street"),
                        (String) addObj.get("city"), (String) addObj.get("address.zip"), (String) addObj.get("address.state"),
                        (String) addObj.get("country"), (String) dbObj.get("profilePic"), (String) comObj.get("name"),
                        (String) comObj.get("website"));
            } else {
                if (addObj != null) {
                    user = new User((String) dbObj.get("_id"), (String) dbObj.get("firstName"), (String) dbObj.get("lastName"),
                            (String) dbObj.get("email"), (String) dbObj.get("dateCreated"), (String) addObj.get("street"),
                            (String) addObj.get("city"), (String) addObj.get("zip"), (String) addObj.get("state"),
                            (String) addObj.get("country"), (String) dbObj.get("profilePic"), null, null);
                } else {
                    if (comObj != null) {
                        user = new User((String) dbObj.get("_id"), (String) dbObj.get("firstName"), (String) dbObj.get("lastName"),
                                (String) dbObj.get("email"), (String) dbObj.get("dateCreated"), null, null, null, null, null,
                                (String) dbObj.get("profilePic"), (String) comObj.get("name"), (String) comObj.get("website"));
                    } else {
                        user = new User((String) dbObj.get("_id"), (String) dbObj.get("firstName"), (String) dbObj.get("lastName"),
                                (String) dbObj.get("email"), (String) dbObj.get("dateCreated"), null, null, null, null, null,
                                (String) dbObj.get("profilePic"), null, null);
                    }
                }
            }
            return user;
        }
        return null;
    }

    public static DBObject updateFileds(User user, DBObject obj) {
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

    public static User createNewUser(String id, String firstName, String lastName, String email, String dateCreated, String street,
                                     String city, String zip, String state, String country, String profilePic, String name,
                                     String website){
        return new User(id, firstName, lastName, email, dateCreated, street, city, zip, state, country,
                profilePic, name, website);
    }
}
