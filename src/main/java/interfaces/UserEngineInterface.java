package interfaces;

//////////////////////////////////////////////////////////////////////////////
// UserEngineInterface.java - Defines UserEngineInterface for MongoDB       //
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
 * This File defines interface with CRUD methods to modify and retrieve data from database (MongoDB)
 *
 *
 * Primary Use -    To be used by UserController for serving REST API calls
 *                  Return back results of CRUD methods on database back to controller (UserController)
 *
 * Note: DemoData() function, generates pre-processing data
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *      model.User.java;
 *      java.util
 *
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
 * ver 1.0 : 11 Jul 2016
 * - first release
 *
 */

import model.User;
import java.util.List;

public interface UserEngineInterface {
    boolean insertUser(String json);
    List<User> getAllUsers();
    User getUser(String id);
    boolean updateUser(String id, String json);
    boolean demoData();
}
