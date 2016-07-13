package controller.factories;

//////////////////////////////////////////////////////////////////////////////
// ControllerFactory.java - defines methods to get Controller object        //
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
 * Defines Factory class for Controller objects
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *
 *      controller.interface.ControllerInterface
 *      controller.UserController
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

// imports
import controller.UserController;
import controller.interfaces.ControllerInterface;

// factory class to get new Controller objects
public class ControllerFactory {
    // method to get different types of new Controller objects
    public ControllerInterface getController(String type) {
        if(type != null || !type.isEmpty()) {
            if (type.equalsIgnoreCase("UserController")) {
                return new UserController();
            }
        }
        return null;
    }
}
