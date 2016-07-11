package controller;

//////////////////////////////////////////////////////////////////////////////
// UserController.java - Controller for REST APIs - Defines Routes in Spark //
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
 * This file acts as Controller for User type.
 * Starts SPARK application
 * Defines Routes for different types of requests from server
 * Uses UserEngine and Util.* files to perform necessary operations on received requests
 * and provide respective response.
 * If request is successful response will be of type application/json
 * If request is unsuccessful response will be of type type/html with Status code 200 and message stating cause
 * Note: If required we can convert unsuccessful request's response to application/json type - use toJson(ErrorClass type)
 *
 * Note: Uncomment DemoData() line, if no requirement for previously generated data
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *      model.*;
 *      util.ErrorClass.java
 *      util.JsonUtil.java
 *      Google gson
 *      spark.SPARK
 *
 * Build Process:
 *      DevEnv : mvn install
 *      DepEnv : mvn clean deploy
 *
 * Reference:   Spark Documentation : http://sparkjava.com/documentation.html
 * ----------   Spark Tutorials : https://sparktutorials.github.io/
 *              Google Gson User-Guide : https://sites.google.com/site/gson/gson-user-guide
 *              Building Simple REST API with Java Spark : https://dzone.com/articles/building-simple-restful-api
 *
 *
 * Maintenance History:
 * --------------------
 * ver 1.0 : 10 Jul 2016
 * - first release
 *
 */


//imports
import com.google.gson.*;
import model.*;
import util.ErrorClass;
//static imports
import static spark.Spark.*;
import static util.JsonUtil.toJson;

public class UserController {

    static {
        // to change default port
        // Spark.port("int val - port number");
        // to use theardpool
        // Spark.threadPool("int val - max number of threads");
    }

    private UserEngine userEngine;

    public UserController(UserEngine userEngine) {
        this.userEngine = userEngine;
        // add some demo data
        this.userEngine.demoData();
        // setup routing points
        setupRequestPoints();
    }

    private void setupRequestPoints() {
        get("/users", (req, res) -> {
            JsonArray jArr = new JsonArray();
            for (User user : this.userEngine.getAllUsers()) {
                jArr.add(new JsonParser().parse(toJson(user)));
            }
            if (jArr.size() > 0) {
                res.type("application/json");
                res.status(302);  // HTTP code for Found
                return jArr;
            } else {
                res.type("text/html");
                res.status(200);  // HTTP code for OK
                return ((new ErrorClass("There are no user entries in database")).getMessage());
            }
        });

        post("/users", (req, res) -> {
            String body = req.body();
            if (this.userEngine.insertUser(body)) {
                res.redirect("/users");
                res.status(201);  //HTTP Code for Created
                return "";
            } else {
                res.type("text/html");
                res.status(200);
                return ((new ErrorClass("Conflict Occurred: User with id already present in database")).getMessage());
            }
        });

        get("/user/:id", (req, res) -> {
            String id = req.params(":id");
            User user = userEngine.getUser(id);
            if (user != null) {
                res.type("application/json");
                res.status(302);
                return new JsonParser().parse(toJson(user));
            }
            res.type("text/html");
            res.status(200);
            return ((new ErrorClass("User with id " + id + " not found")).getMessage());
        });

        put("/user/:id", (req, res) -> {
            String id = req.params(":id");
            String body = req.body();
            if (this.userEngine.updateUser(id, body)) {
                res.type("application/json");
                res.status(302);
                User user = this.userEngine.getUser(id);
                return new JsonParser().parse(toJson(user));
            }
            res.type("text/html");
            res.status(200);
            return ((new ErrorClass("User with id " + id + " not found")).getMessage());
        });

        get("/:anything", (req, res) -> {
            res.type("text/html");
            res.status(501);  // HTTP code for Not Implemented
           return ((new ErrorClass(" Request not implemented... Stay tuned!!")).getMessage());
        });

        post("/:anything", (req, res) -> {
            String any = req.params(":anything");
            res.redirect("/"+any);
            return "";
        });

        put("/:anything", (req, res) -> {
            String any = req.params(":anything");
            res.redirect("/"+any);
            return "";
        });

        delete("/:anything", (req, res) -> {
            String any = req.params(":anything");
            res.redirect("/"+any);
            return "";
        });

        options("/:anything", (req, res) -> {
            String any = req.params(":anything");
            res.redirect("/"+any);
            return "";
        });
    }
}
