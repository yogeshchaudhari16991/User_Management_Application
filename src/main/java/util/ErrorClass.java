package util;

//////////////////////////////////////////////////////////////////////////////
// ErrorClass.java - Utility file - Defines ErrorClass Object Type          //
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
 * Defines ErrorClass object type used by UserController to send back response message
 * for unsuccessful requests
 *
 * Note:
 * Defined so that if required we can modify response type and convert it to Json easily.
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
 * Reference:   Building Simple REST API with Java Spark : https://dzone.com/articles/building-simple-restful-api
 * ----------
 *
 * Maintenance History:
 * --------------------
 * ver 1.0 : 10 Jul 2016
 * - first release
 *
 */

public class ErrorClass {

    // encpsulated fields
    private String message;

    // Public Constructors
    public ErrorClass(String message) {
        this.message = message;
    }

    public ErrorClass(Exception e) {
        this.message = e.getMessage();
    }

    public ErrorClass(Error e) {
        this.message = e.getMessage();
    }

    public String getMessage() {
        return this.message;
    }
}
