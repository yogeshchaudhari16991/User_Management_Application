package model;

/**
 * Created by yoges on 7/8/2016.
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
