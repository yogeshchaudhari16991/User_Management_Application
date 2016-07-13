package controller.interfaces;

//////////////////////////////////////////////////////////////////////////////
// DBInterface.java - Defines DBInterface for DBObjects                      //
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
 * This File defines interface with get methods for DB objects
 *
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files: None
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

public interface DBInterface<Database,Collection> {
    Database getDB(String databaseName);
    Collection getDB(String databaseName, String collectionName);
}
