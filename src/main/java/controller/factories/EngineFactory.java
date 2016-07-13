package controller.factories;

//////////////////////////////////////////////////////////////////////////////
// EngineFactory.java - defines methods to get UserEngine object       //
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
 * Defines Factory class for UserEngine objects
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *
 *      controller.interface.UserEngineInterface
 *      controller.mongoUserEngine
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
 * ver 1.0 : 10 Jul 2016
 * - first release
 * ver 1.0.1 : 11 Jul 2016
 * - Interfaces and Factories added
 *
 */


//imports
import controller.MongoUserEngine;
import controller.interfaces.UserEngineInterface;

// factory class to provide UserEngine objects
public class EngineFactory {
    // method to get different types of new userEngine object
    public UserEngineInterface getUserEngine(String type) {
        if(type != null || !type.isEmpty()) {
            if (type.equalsIgnoreCase("MongoUserEngine")) {
                return new MongoUserEngine();
            }
        }
        return null;
    }
}
