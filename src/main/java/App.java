

///////////////////////////////////////////////////////////////////////////////////
// App.java - Starting Point for Application - Defines Entry point Main() method //
// Ver 1.0                                                                       //
// Application: User Management Application                                      //
// Language:    Java, ver 8, IntelliJ IDEA 2016.1.3                              //
// Platform:    Dell Inspiron 14 5000Series, Core-i5, Windows 10                 //
// Author:      Yogesh Chaudhari, Intern, Syracuse University                    //
//              (315) 4809210, yogeshchaudhari16991@gmail.com                    //
///////////////////////////////////////////////////////////////////////////////////
/*
 * File Operations:
 * -------------------
 *
 * This file implements main() method - entry point for Application
 * Using DBCollection method starts MongoDB client and gets Collection data - DB
 * In main method passes retrieved collection object to new MongoUserEngine object
 * Passes MongoUserEngine object to new UserController object
 * By creating UserController object starts Spark application
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *      Java MongoDB driver
 *      controller.factories.*
 *      controller.interface.*
 *
 * Build Process:
 *      DevEnv : mvn install
 *      DepEnv : mvn clean deploy
 *
 * Reference:   None
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
import com.mongodb.DBCollection;
import controller.factories.ControllerFactory;
import controller.factories.DBFactory;
import controller.factories.EngineFactory;
import controller.interfaces.ControllerInterface;
import controller.interfaces.DBInterface;
import controller.interfaces.UserEngineInterface;


//main Application class
public class App {
    private static final boolean DEPLOYED = false;
    // entry method for app - Main method
    public static void main(String[] args) {
        try {
            DBFactory dbFactory = new DBFactory();
            DBInterface mongo = dbFactory.getDBType("MongoDB");
            DBCollection collection = (DBCollection) mongo.getDB("UserManagementSystems", "Users");
            EngineFactory engineFactory = new EngineFactory();
            UserEngineInterface userEngine = engineFactory.getUserEngine("MongoUserEngine");
            userEngine.assignCollection(collection, DEPLOYED);
            ControllerFactory controllerFactory = new ControllerFactory();
            ControllerInterface controller = controllerFactory.getController("UserController");
            controller.assignEngine(userEngine);
            controller.setupRequestPoints();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
