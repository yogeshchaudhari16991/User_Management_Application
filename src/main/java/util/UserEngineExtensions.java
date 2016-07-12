package util;

//////////////////////////////////////////////////////////////////////////////////////////////
// UserEngineExtensions.java - Utility file - Defines helper methods for userEngine class   //
// Ver 1.0                                                                                  //
// Application: User Management Application                                                 //
// Language:    Java, ver 8, IntelliJ IDEA 2016.1.3                                         //
// Platform:    Dell Inspiron 14 5000Series, Core-i5, Windows 10                            //
// Author:      Yogesh Chaudhari, Intern, Syracuse University                               //
//              (315) 4809210, yogeshchaudhari16991@gmail.com                               //
//////////////////////////////////////////////////////////////////////////////////////////////
/*
 * File Operations:
 * -------------------
 *
 * Defines Helper methods for UserEngine class
 * Uses Util.jsonUtil file to provide UserEngine with results for data type conversion
 * from User object to DBObject
 * and
 * from DBObject to User object
 * Defines a updateField() method to update DBObject fields and convert it to User type Object
 * Defines a helper method to create new User object
 * Defines helper method to validate Address and Company Object fields
 *
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *      model.User.java
 *      model.Address.java
 *      model.Company.java
 *      Java MongoDB driver
 *
 * Build Process:
 *      DevEnv : mvn install
 *      DepEnv : mvn clean deploy
 *
 * Reference: Java MongoDB Driver Documentation : https://docs.mongodb.com/ecosystem/drivers/java/
 * ----------
 *
 * Maintenance History:
 * --------------------
 * ver 1.0 : 10 Jul 2016
 * - first release
 *
 */

// imports
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import model.Address;
import model.Company;
import model.User;

public class UserEngineExtensions {

    // Static Utility methods

    //method to convert User object to DBObject
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
        if(user.getDateCreated() != null){
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

    // method to convert DBObject to User object
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
                 user = new User((Long) dbObj.get("_id"), (String) dbObj.get("firstName"), (String) dbObj.get("lastName"),
                        (String) dbObj.get("email"), dbObj.get("dateCreated").toString(), (String) addObj.get("street"),
                        (String) addObj.get("city"), (String) addObj.get("zip"), (String) addObj.get("state"),
                        (String) addObj.get("country"), (String) dbObj.get("profilePic"), (String) comObj.get("name"),
                        (String) comObj.get("website"));
            } else {
                if (addObj != null) {
                    user = new User((Long) dbObj.get("_id"), (String) dbObj.get("firstName"), (String) dbObj.get("lastName"),
                            (String) dbObj.get("email"), dbObj.get("dateCreated").toString(), (String) addObj.get("street"),
                            (String) addObj.get("city"), (String) addObj.get("zip"), (String) addObj.get("state"),
                            (String) addObj.get("country"), (String) dbObj.get("profilePic"), null, null);
                } else {
                    if (comObj != null) {
                        user = new User((Long) dbObj.get("_id"), (String) dbObj.get("firstName"), (String) dbObj.get("lastName"),
                                (String) dbObj.get("email"), dbObj.get("dateCreated").toString(), null, null, null, null, null,
                                (String) dbObj.get("profilePic"), (String) comObj.get("name"), (String) comObj.get("website"));
                    } else {
                        user = new User((Long) dbObj.get("_id"), (String) dbObj.get("firstName"), (String) dbObj.get("lastName"),
                                (String) dbObj.get("email"), dbObj.get("dateCreated").toString(), null, null, null, null, null,
                                (String) dbObj.get("profilePic"), null, null);
                    }
                }
            }
            return user;
        }
        return null;
    }

    // method to update DBObject fields
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
        if(user.getDateCreated() != null){
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

    // static method to create new instance of User type
    public static User createNewUser(Long id, String firstName, String lastName, String email, String dateCreated, String street,
                                     String city, String zip, String state, String country, String profilePic, String name,
                                     String website){
        return new User(id, firstName, lastName, email, dateCreated, street, city, zip, state, country,
                profilePic, name, website);
    }

    //method to validate Company object
    public static boolean companyValid(Company company) {
        if(company == null){
            return false;
        }
        return companyValid(company.getName(), company.getWebsite());
    }

    //method to validate Company object's variables
    public static boolean companyValid(String name, String website) {
        return (name != null || website != null) && (!name.isEmpty() || !website.isEmpty());
    }

    //method to validate Address object
    public static boolean addressValid(Address address) {
        if(address == null){
            return false;
        }
        return addressValid(address.getStreet(), address.getCity(), address.getZip(), address.getState(), address.getCountry());
    }

    //method to validate Address object's variables
    public static boolean addressValid(String street, String city, String zip, String state, String country) {
        return (street != null || city != null || zip != null || state != null || country != null) &&
                (!street.isEmpty() || !city.isEmpty() || !zip.isEmpty() || !state.isEmpty() || !country.isEmpty());
    }

}
