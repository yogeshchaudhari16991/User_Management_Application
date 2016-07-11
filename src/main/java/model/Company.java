package model;

//////////////////////////////////////////////////////////////////////////////
// Company.java - Define Company Object type for User type                  //
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
 * Implements Company type used by User type
 *
 * Stores Company information for User
 *
 * Company Structure:
 * "name":"Denesik Group",
 * "website":"http://jodie.org"
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *      None
 *
 * Build Process:
 *      DevEnv : mvn install
 *      DepEnv : mvn clean deploy
 *
 * Reference: None
 * ----------
 *
 *
 * Maintenance History:
 * --------------------
 * ver 1.0 : 10 Jul 2016
 * - first release
 *
 */


// Company Object Type
public class Company{

    /* Company Structure
        "name":"Denesik Group",
        "website":"http://jodie.org"
     */

    //encapsulated fields
    private String name;
    private String website;

    // Public Constructors
    public Company() {
        super();
    }

    public Company(String name, String website) {
        this();
        this.name = name;
        this.website = website;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    // Override toString method
    @Override
    public String toString() {
        return ("{" +
                "\n\t Name: " + name +
                "\n\t Website: " + website +
                "\n}");
    }
}
