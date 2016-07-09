package model;

/**
 * Created by yoges on 7/8/2016.
 */
// Company Object Type
public class Company{
    private String name;
    private String website;

    // constructors
    public Company() {
    }

    public Company(String name, String website) {
        super();
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
}
