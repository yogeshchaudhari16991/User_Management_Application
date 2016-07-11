
package model;

//////////////////////////////////////////////////////////////////////////////
// User.java - Define User Object type                                      //
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
 * Implements User type and uses Address Type and Company Type
 *
 * Stores User Information
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *      Util.UserEngineExtensions.java
 *      model.Address.java
 *      model.Company.java
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

// static imports
import static util.UserEngineExtensions.addressValid;
import static util.UserEngineExtensions.companyValid;

public class User {

    /* User Structure:
         {
           "id":"1630215c-2608-44b9-aad4-9d56d8aafd4c",
           "firstName":"Dorris",
           "lastName":"Keeling",
           "email":"Darby_Leffler68@gmail.com",
           "address":{
              "street":"193 Talon Valley",
              "city":"South Tate furt",
              "zip":"47069",
              "state":"IA",
              "country":"US"
           },
           "dateCreated":"2016-03-15T07:02:40.896Z",
           "company":{
              "name":"Denesik Group",
              "website":"http://jodie.org"
           },
           "profilePic":"http://lorempixel.com/640/480/people"
        }
     */

    // encapsulated fields
    // Later try to use private static final long serialVersionUID = 42L;
    // try to use Date instead of String
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String dateCreated;
    private Company company;
    private String profilePic;


    // Public Constructor
    public User() {
        super();
    }

    public User(String id, String firstName, String lastName, String email, String dateCreated, String street, String city, String zip,
                String state, String country, String profilePic, String name, String website) {
        this();
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.firstName = firstName;
        this.dateCreated = dateCreated;
        if(addressValid(street, city, zip, state, country)) {
            this.address = new Address(street, city, zip, state, country);
        }
        this.profilePic = profilePic;
        if(companyValid(name, website)) {
            this.company = new Company(name, website);
        }
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company comapny) {
        this.company = comapny;
    }

    // Override toString method
    @Override
    public String toString() {
        return ("User: " +
                "\n ID: " + this.getId() + "" +
                "\n First Name: " + this.getFirstName() +
                "\n Last Name:" + this.getLastname() +
                "\n Email: " + this.getEmail() +
                "\n Address: " + this.getAddress() +
                "\n Date Created: " + this.getDateCreated() +
                "\n Company: " + this.getCompany() +
                "\n Profile Pic: " + this.getProfilePic()
        );
    }
}
