

//////////////////////////////////////////////////////////////////////////////////////////////////////////
// UserEngineTest.java - JUnit Test file - Defines unit test methods for User Management Application    //
// Ver 1.0                                                                                              //
// Application: User Management Application                                                             //
// Language:    Java, ver 8, IntelliJ IDEA 2016.1.3                                                     //
// Platform:    Dell Inspiron 14 5000Series, Core-i5, Windows 10                                        //
// Author:      Yogesh Chaudhari, Intern, Syracuse University                                           //
//              (315) 4809210, yogeshchaudhari16991@gmail.com                                           //
//////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
 * File Operations:
 * -------------------
 *
 * Defines Test cases for User Management Application
 * Defines TestResponse class for converting user-data received from Response to required User object type
 *
 * Important Note:  Before running UserEngineTest.java uncomment folowing lines:
 * ---------------  collection.remove() line in App.java - mongo() method
 *                  userEngine.demoData() line in UserController.java - constructor method
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *      model.User.java
 *      JUnit Library
 *      spark.SPARK
 *      spark.utils
 *      Google Gson
 *      java.io
 *      java.net
 *      java.util
 *
 * Build Process:
 *      DevEnv : mvn install
 *      DepEnv : mvn clean deploy
 *
 * Reference: Java MongoDB Driver Documentation : https://docs.mongodb.com/ecosystem/drivers/java/
 * ----------
 *
 * Maintenance History:
 * --------------------
 * ver 1.0 : 10 Jul 2016
 * - first release
 *
 */

import com.google.gson.*;
import model.User;
import org.junit.*;
import spark.Spark;
import spark.utils.IOUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
// static imports
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

// Test Class
public class UserEngineTest {

    private List<User> users = new ArrayList<User>();
    private User user;

    @BeforeClass
    public static void beforeClass() {
        App.main(null);
    }

    @Before
    public void before(){
        // Before running UserEngineTest.java uncomment
        // collection.remove() line in App.java - mongo() method
        // userEngine.demoData() line in UserController.java - constructor method
        //
        // due to DemoData() method in UserEngine.java there will be 5 users with id ranging from 1000 to 1004
        // already in mongoDB collection
        //
        // if database already contains some demo data uncomment these lines
        TestResponse res = request("GET", "/users", null);
        users = res.jsonArr();
    }

    @Test
    // Testing method for creation of new user
    // case 1: User does not already exist in Collection
    // case 2: User already exists in Collection
    public void createNewUser() {
        int initialSize = users.size();
        String jsonReq = "{\"id\":\"1010\",\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"email\":\"Darby_Leffler68@gmail.com\",\"address\":{\"city\":\"city\",\"street\":\"street\",\"zip\":\"zip\",\"state\":\"state\",\"country\":\"country\"},\"dateCreated\":\"2016-03-15T07:02:40.896Z\",\"company\":{\"name\":\"name\",\"website\":\"website\"},\"profilePic\":\"profilePic\"}";
        TestResponse res = request("POST", "/users", jsonReq);
        if(res.status == 302) {
            users = res.jsonArr();
            assertEquals((++initialSize), users.size());
        } else if (res.status == 200){
            assertEquals("Conflict Occurred: Either User with id already present in database OR Date format was Wrong." +
                    "Correct Date Format is \" YYYY-MM-dd'T'hh:mm:ss.S'Z' \" and " +
                    "example: 2016-03-15T07:02:40.896Z", res.body);
        } else {
            fail("createNewUser Test Failed");
        }
        jsonReq = "{\"id\":\"1010\",\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"email\":\"Darby_Leffler68@gmail.com\",\"address\":{\"city\":\"city\",\"street\":\"street\",\"zip\":\"zip\",\"state\":\"state\",\"country\":\"country\"},\"dateCreated\":\"2016-03-15T07:02:40.896Z\",\"company\":{\"name\":\"name\",\"website\":\"website\"},\"profilePic\":\"profilePic\"}";
        res = request("POST", "/users", jsonReq);
        if(res.status == 302) {
            users = res.jsonArr();
            assertEquals((++initialSize), users.size());
        } else if (res.status == 200){
            assertEquals("Conflict Occurred: Either User with id already present in database OR Date format was Wrong." +
                    "Correct Date Format is \" YYYY-MM-dd'T'hh:mm:ss.S'Z' \" and " +
                    "example: 2016-03-15T07:02:40.896Z", res.body);
        } else {
            fail("createNewUser Test Failed");
        }
    }

    @Test
    // Testing method for getting all users from Collection
    // Case 1: There are Users documents in Collection
    // Case 2: There are no Users documents in Collection
    public void getAllUsers() {
        int initialSize = users.size();
        TestResponse res = request("GET", "/users", null);
        if(res.status == 302) {
            users = res.jsonArr();
            assertEquals(initialSize, users.size());
        } else if (res.status == 200){
            assertEquals("There are no user entries in database", res.body);
        } else {
            fail("getAllUsers Test Failed");
        }
    }

    @Test
    // Testing method to get User specified by user ID
    // Case 1: User exists in collection
    // Case 2: User does not exist in collection
    public void getUser() {
        String id = "1000";
        TestResponse res = request("GET", "/user/"+id, null);
        if (res != null) {
            if(res.status == 302) {
                user = res.json();
                assertEquals(id, user.getId());
            } else if (res.status == 200){
                assertEquals("User with id 1000 not found", res.body);
            } else {
                fail("getUsers Test Failed");
            }
        }
        id = "1006";
        res = request("GET", "/user/"+id, null);
        if (res != null) {
            if(res.status == 302) {
                user = res.json();
                assertEquals(id, user.getId());
            } else if (res.status == 200){
                assertEquals("User with id 1006 not found", res.body);
            } else {
                fail("getUsers Test Failed");
            }
        }
    }

    @Test
    // Testing method to update User specified by user ID
    // Case 1: User exists in collection
    // Case 2: User does not exist in collection
    public void updateUser() {
        String jsonReq = "{\"id\":\"1000\",\"firstName\":\"firstName-Edited\",\"lastName\":\"lastName\",\"email\":\"Darby_Leffler68@gmail.com\",\"address\":{\"city\":\"city\",\"street\":\"street\",\"zip\":\"zip\",\"state\":\"state\",\"country\":\"country\"},\"dateCreated\":\"2016-03-15T07:02:40.896Z\",\"company\":{\"name\":\"name\",\"website\":\"website\"},\"profilePic\":\"profilePic\"}";
        TestResponse res = request("PUT", "/user/1000", jsonReq);
        if (res != null) {
            if(res.status == 302) {
                user = res.json();
                assertEquals("1000", user.getId());
                assertEquals("firstName-Edited", user.getFirstName());
            } else if (res.status == 200){
                assertEquals("Error Occurred: Either User with id 1000 is not found in database OR Date format was Wrong." +
                        "Correct Date Format is \" YYYY-MM-dd'T'hh:mm:ss.S'Z' \" and " +
                        "example: 2016-03-15T07:02:40.896Z", res.body);
            } else {
                fail("updateUser Test Failed");
            }
        }
        jsonReq = "{\"id\":\"1006\",\"firstName\":\"firstName-Edited\",\"lastName\":\"lastName\",\"email\":\"Darby_Leffler68@gmail.com\",\"address\":{\"city\":\"city\",\"street\":\"street\",\"zip\":\"zip\",\"state\":\"state\",\"country\":\"country\"},\"dateCreated\":\"2016-03-15T07:02:40.896Z\",\"company\":{\"name\":\"name\",\"website\":\"website\"},\"profilePic\":\"profilePic\"}";
        res = request("PUT", "/user/1006", jsonReq);
        if (res != null) {
            if(res.status == 302) {
                user = res.json();
                assertEquals("1006", user.getId());
                assertEquals("firstName-Edited", user.getFirstName());
            } else if (res.status == 200){
                assertEquals("Error Occurred: Either User with id 1006 is not found in database OR Date format was Wrong." +
                    "Correct Date Format is \" YYYY-MM-dd'T'hh:mm:ss.S'Z' \" and " +
                    "example: 2016-03-15T07:02:40.896Z", res.body);
            } else {
                fail("updateUser Test Failed");
            }
        }
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

    // method to generate HTTPURLConnection
    // and send request user-data in body if required
    // receive response user-data back
    private TestResponse request(String method, String path, String jsonReq) {
        URL url;
        HttpURLConnection connection;
        String body;
        switch (method.toLowerCase()) {
            case "post":
            case "put": {
                try {
                    url = new URL("http://localhost:4567" + path);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod(method);
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Content-Length", Integer.toString(jsonReq.length()));
                    BufferedWriter httpRequestBodyWriter =
                            new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                    httpRequestBodyWriter.write(jsonReq);
                    httpRequestBodyWriter.close();
                    connection.connect();
                    body = IOUtils.toString(connection.getInputStream());
                    return new TestResponse(connection.getResponseCode(), body);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    fail("Sending request failed: " + e.getMessage());
                    return null;
                } catch (ProtocolException e) {
                    e.printStackTrace();
                    fail("Sending request failed: " + e.getMessage());
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    fail("Sending request failed: " + e.getMessage());
                    return null;
                }
            }
            case "get": {
                try {
                    url = new URL("http://localhost:4567" + path);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod(method);
                    connection.connect();
                    body = IOUtils.toString(connection.getInputStream());
                    return new TestResponse(connection.getResponseCode(), body);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    fail("Sending request failed: " + e.getMessage());
                    return null;
                } catch (ProtocolException e) {
                    e.printStackTrace();
                    fail("Sending request failed: " + e.getMessage());
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    fail("Sending request failed: " + e.getMessage());
                    return null;
                }
            }
            default:
                return null;
        }
    }

    // TestResponse class - Defined to easily convert received Response user-data
    // from JSON to User object type
    private static class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

        public List<User> jsonArr() {
            JsonArray jArr = new JsonParser().parse(body).getAsJsonArray();
            List<User> users = new ArrayList<User>();
            for(JsonElement jEle : jArr ){
                users.add(new Gson().fromJson(jEle, User.class));
            }
            return users;
        }

        public User json() {
            return new Gson().fromJson(body, User.class);
        }
    }
}
