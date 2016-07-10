/**
 * Created by yoges on 7/8/2016.
 */

package model;

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

    // User fields
    // Later try to use private static final long serialVersionUID = 42L;
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String dateCreated;
    //private Date dateCreated = new Date(); /* Later Convert to Date type if possible */
    private Company company;
    private String profilePic;


    // constructor
    public User() {
        super();
        this.address = new Address();
        this.company = new Company();
    }

    public User(String id, String firstName, String lastName, String email, String dateCreated, String street, String city, String zip,
                String state, String country, String profilePic, String name, String website) {
        this();
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.firstName = firstName;
        this.dateCreated = dateCreated;
        this.address.generateAddress(street, city, zip, state, country);
        this.profilePic = profilePic;
        this.company.generateCompany(name, website);
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

    @Override
    public String toString(){
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
