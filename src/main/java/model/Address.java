package model;

//////////////////////////////////////////////////////////////////////////////
// Address.java - Define Address Object Type for User                       //
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
 * This package implements Address type used by User type.
 *
 * Address consists of physical address information of User.
 *
 * Address Structure:
 * "street":"193 Talon Valley",
 * "city":"South Tate furt",
 * "zip":"47069",
 * "state":"IA",
 * "country":"US"
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
 * Maintenance History:
 * --------------------
 * ver 1.0 : 10 Jul 2016
 * - first release
 *
 */

// Address Object Type
public class Address{

    /* Address Structure:
        "street":"193 Talon Valley",
        "city":"South Tate furt",
        "zip":"47069",
        "state":"IA",
        "country":"US"
     */

    //encapsulated fields
    private String street;
    private String city;
    private String zip;
    private String state;
    private String country;

    // Public Constructors
    public Address() {
        super();
    }

    public Address(String street, String city, String zip, String state, String country) {
        this();
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.country = country;
    }

    // getters and setters
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Override toString method
    @Override
    public String toString() {
        return ("{" +
                "\n\t Street: " + street +
                "\n\t City: " + city +
                "\n\t Zip: " + zip +
                "\n\t State: " + state +
                "\n\t Country: " + country +
                "\n}");
    }
}
