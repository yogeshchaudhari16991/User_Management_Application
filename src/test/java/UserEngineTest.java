

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
 *
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
        // database already contains some demo data
        TestResponse res = request("GET", "/users", null);
        users = res.jsonArr();
    }

    @Test
    public void createNewUser() {
        int initialSize = users.size();
        String jsonReq = "{\"id\":\"1010\",\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"email\":\"email\",\"address\":{\"city\":\"city\",\"street\":\"street\",\"zip\":\"zip\",\"state\":\"state\",\"country\":\"country\"},\"dateCreated\":\"dateCreated\",\"company\":{\"name\":\"name\",\"website\":\"website\"},\"profilePic\":\"profilePic\"}";
        TestResponse res = request("POST", "/users", jsonReq);
        users = res.jsonArr();
        assertEquals((++initialSize), users.size());
        assertEquals(302, res.status);
        jsonReq = "{\"id\":\"1010\",\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"email\":\"email\",\"address\":{\"city\":\"city\",\"street\":\"street\",\"zip\":\"zip\",\"state\":\"state\",\"country\":\"country\"},\"dateCreated\":\"dateCreated\",\"company\":{\"name\":\"name\",\"website\":\"website\"},\"profilePic\":\"profilePic\"}";
        res = request("POST", "/users", jsonReq);
        assertEquals("Conflict Occurred: User with id already present in database", res.body);
        assertEquals(200, res.status);
    }

    @Test
    public void getAllUsers() {
        int initialSize = users.size();
        TestResponse res = request("GET", "/users", null);
        if(res.status == 302) {
            users = res.jsonArr();
            assertEquals(initialSize, users.size());
            assertEquals(302, res.status);
        } else {
            assertEquals("There are no user entries in database", res.body);
            assertEquals(200, res.status);
        }
    }

    @Test
    public void getUser() {
        String id = "1000";
        TestResponse res = request("GET", "/user/1000", null);
        if (res != null) {
            user = res.json();
            assertEquals("1000", user.getId());
            assertEquals(302, res.status);
        }
        res = request("GET", "/user/1006", null);
        if (res != null) {
            assertEquals("User with id 1006 not found", res.body);
            assertEquals(200, res.status);
        }
    }

    @Test
    public void updateUser() {
        String jsonReq = "{\"id\":\"1000\",\"firstName\":\"firstName-Edited\",\"lastName\":\"lastName\",\"email\":\"email\",\"address\":{\"city\":\"city\",\"street\":\"street\",\"zip\":\"zip\",\"state\":\"state\",\"country\":\"country\"},\"dateCreated\":\"dateCreated\",\"company\":{\"name\":\"name\",\"website\":\"website\"},\"profilePic\":\"profilePic\"}";
        TestResponse res = request("PUT", "/user/1000", jsonReq);
        if (res != null) {
            user = res.json();
            assertEquals("1000", user.getId());
            assertEquals("firstName-Edited", user.getFirstName());
            assertEquals(302, res.status);
        }
        jsonReq = "{\"id\":\"1006\",\"firstName\":\"firstName-Edited\",\"lastName\":\"lastName\",\"email\":\"email\",\"address\":{\"city\":\"city\",\"street\":\"street\",\"zip\":\"zip\",\"state\":\"state\",\"country\":\"country\"},\"dateCreated\":\"dateCreated\",\"company\":{\"name\":\"name\",\"website\":\"website\"},\"profilePic\":\"profilePic\"}";
        res = request("PUT", "/user/1006", jsonReq);
        if(res != null) {
            assertEquals("User with id 1006 not found", res.body);
            assertEquals(200, res.status);
        }
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

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
