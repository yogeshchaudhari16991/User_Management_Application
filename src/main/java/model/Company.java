package model;

/**
 * Created by yoges on 7/8/2016.
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
