package util;

//////////////////////////////////////////////////////////////////////////////////////
// ErrorClass.java - Utility file - Defines helper methods for use of Google Gson   //
// Ver 1.0                                                                          //
// Application: User Management Application                                         //
// Language:    Java, ver 8, IntelliJ IDEA 2016.1.3                                 //
// Platform:    Dell Inspiron 14 5000Series, Core-i5, Windows 10                    //
// Author:      Yogesh Chaudhari, Intern, Syracuse University                       //
//              (315) 4809210, yogeshchaudhari16991@gmail.com                       //
//////////////////////////////////////////////////////////////////////////////////////
/*
 * File Operations:
 * -------------------
 *
 * Defines Helper methods to use Google Gson object easily for conversion and mapping of
 * Object type to Json
 * and
 * Json to Object type
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *      model.User.java
 *      Google Gson
 *
 *
 * Build Process:
 *      DevEnv : mvn install
 *      DepEnv : mvn clean deploy
 *
 * Reference:   Google Gson User-Guide : https://sites.google.com/site/gson/gson-user-guide
 * ----------
 *
 * Maintenance History:
 * --------------------
 * ver 1.0 : 10 Jul 2016
 * - first release
 *
 */

// imports
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import model.User;

public class JsonUtil {

    private static Gson g = new GsonBuilder().setPrettyPrinting().create();

    // Static Utility methods
    public static String toJson(Object object) {
        return (g.toJson(object));
    }

    public static Object fromJson(String json) {
        try {
            User user = g.fromJson(json, User.class);
            return user;
        } catch (JsonSyntaxException e){
            return null;
        }
    }
}
