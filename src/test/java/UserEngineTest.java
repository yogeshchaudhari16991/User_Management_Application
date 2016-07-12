

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
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import controller.UserController;
import controller.UserEngine;
import model.User;
import org.junit.*;
import spark.Spark;
import spark.utils.IOUtils;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
// static imports
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static util.JsonUtil.toJson;

// Test Class
public class UserEngineTest {

    private List<User> users = new ArrayList<User>();
    private static final boolean TEST = true;

    @BeforeClass
    public static void beforeClass() {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient();
            DB database = mongoClient.getDB("UserManagementSystem");
            DBCollection collection = database.getCollection("UsersTest");
            new UserController(new UserEngine(collection, TEST));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            fail("Unknown Host");
        }

    }

    @Before
    public void before(){
        // due to DemoData() method in UserEngine.java there will be 5 users with id ranging from 1000 to 1004
        // already in mongoDB collection
        //
        TestResponse res = request("GET", "/users", null);
        users = res.jsonArr();
    }

    @Test
    // Testing method for creation of new user
    // case 1: User does not already exist in Collection
    // case 2: User already exists in Collection
    public void createNewUser() {
        int initialSize = users.size();
        User user = users.get(0);
        user.setId(UUID.randomUUID());
        String jsonReq = toJson(user);
        TestResponse res = request("POST", "/users", jsonReq);
        if(res.status == 302) {
            users = res.jsonArr();
            assertEquals((++initialSize), users.size());
        } else if (res.status == 200){
            assertEquals("Conflict Occurred: Either User with id already present in database OR Email was invalid OR Date format was Wrong." +
                    "Correct Date Format is \" YYYY-MM-dd'T'hh:mm:ss.S'Z' \" and " +
                    "example: 2016-03-15T07:02:40.896Z", res.body);
        } else {
            fail("createNewUser Test Failed");
        }
        // try same request to create new user with same ID
        res = request("POST", "/users", jsonReq);
        if(res.status == 302) {
            users = res.jsonArr();
            assertEquals((++initialSize), users.size());
        } else if (res.status == 200){
            assertEquals("Conflict Occurred: Either User with id already present in database OR Email was invalid OR Date format was Wrong." +
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
        User user = users.get(0);
        UUID id = user.getId();
        TestResponse res = request("GET", "/user/"+id, null);
        if (res != null) {
            if(res.status == 302) {
                user = res.json();
                assertEquals(id, user.getId());
            } else if (res.status == 200){
                assertEquals("User with id " + id + " not found", res.body);
            } else {
                fail("getUsers Test Failed");
            }
        }
        id = UUID.randomUUID();
        res = request("GET", "/user/"+id, null);
        if (res != null) {
            if(res.status == 302) {
                user = res.json();
                assertEquals(id, user.getId());
            } else if (res.status == 200){
                assertEquals("User with id " + id + " not found", res.body);
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
        User user = users.get(0);
        UUID id = user.getId();
        user.setFirstName("firstName-Edited");
        String jsonReq = toJson(user);
        TestResponse res = request("PUT", "/user/" + id, jsonReq);
        if (res != null) {
            if(res.status == 302) {
                user = res.json();
                assertEquals(id, user.getId());
                assertEquals("firstName-Edited", user.getFirstName());
            } else if (res.status == 200){
                assertEquals("Error Occurred: Either User with id" + id + " is not found in database OR Email was invalid OR Date format was Wrong." +
                        "Correct Date Format is \" YYYY-MM-dd'T'hh:mm:ss.S'Z' \" and " +
                        "example: 2016-03-15T07:02:40.896Z", res.body);
            } else {
                fail("updateUser Test Failed");
            }
        }
        user = users.get(0);
        user.setId(UUID.randomUUID());
        id = user.getId();
        jsonReq = toJson(user);
        res = request("PUT", "/user/"+ id, jsonReq);
        if (res != null) {
            if(res.status == 302) {
                user = res.json();
                assertEquals(id, user.getId());
                assertEquals("firstName-Edited", user.getFirstName());
            } else if (res.status == 200){
                assertEquals("Error Occurred: Either User with id " + id + " is not found in database OR Email was invalid OR Date format was Wrong." +
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
        try {
            url = new URL("http://localhost:4567" + path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            switch (method.toLowerCase()) {
                case "post":
                case "put": {
                    try {
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
                    } catch (IOException e) {
                        e.printStackTrace();
                        fail("Sending request failed: " + e.getMessage());
                        return null;
                    } finally {
                        connection.disconnect();
                    }
                }
                case "get": {
                    try {
                        connection.connect();
                        body = IOUtils.toString(connection.getInputStream());
                        return new TestResponse(connection.getResponseCode(), body);
                    } catch (IOException e) {
                        e.printStackTrace();
                        fail("Sending request failed: " + e.getMessage());
                        return null;
                    } finally {
                        connection.disconnect();
                    }
                }
                default:
                    return null;
            }
        } catch (MalformedURLException | ProtocolException  e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
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
