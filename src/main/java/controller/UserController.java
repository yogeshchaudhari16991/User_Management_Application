/**
 * Created by yoges on 7/8/2016.
 */

package controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import com.google.gson.Gson;
import com.sun.javafx.collections.MappingChange;
import model.*;
import util.ErrorClass;

import static util.JsonUtil.json;
import static util.JsonUtil.toJson;

public class UserController {

    private UserEngine userEngine;

    public UserController(UserEngine userEngine) {
        this.userEngine = userEngine;
        this.userEngine.demoData();
        setupRequestPoints();
    }

    private void setupRequestPoints() {
        get("/users", (req, res) -> {
            StringBuilder usersInJSON = new StringBuilder("");
//            System.out.println("In UserController - Size of User List: " + this.userEngine.getAllUsers().size());
            for (User user : this.userEngine.getAllUsers()) {
                //System.out.println(user);
                usersInJSON.append(toJson(user));
            }
            //System.out.println("In UserController - JSON for Users: " + usersInJSON);
            if (usersInJSON.length() > 0) {
                res.type("application/json");
                res.status(302);
                return usersInJSON;
            } else {
                res.status(404);
                return toJson(new ErrorClass("There are no user entries in database"));
            }
        });

        post("/users", (req, res) -> {
            System.out.println("In post");
            String body = req.body();
            if (this.userEngine.insertUser(body)) {
                res.redirect("/users");
                res.status(302);
                return "";
            } else {
                res.status(409);  // HTTP code for Conflict response
                return toJson(new ErrorClass("Conflict Occurred: User with id already present in database"));
            }
        });

        get("/user/:id", (req, res) -> {
            String id = req.params(":id");
            try {
                User user = userEngine.getUser(id);
                if(user != null) {
                    res.type("application/json");
                    res.status(302);
                    return toJson(user);
                }
                res.status(404);
                return toJson(new ErrorClass("User with id " + id + " not found"));
            }
            catch(Exception e) {
                res.status(500);
                return toJson(new ErrorClass(e));
            }
            catch(Error e) {
                res.status(500);
                return toJson(new ErrorClass(e));
            }
        });

        put("/user/:id", (req, res) -> {
            System.out.println("In put");
            String id = req.params(":id");
            String body = req.body();
            if (this.userEngine.updateUser(id, body)) {
                //res.redirect("/users");
                res.status(302);
                return res;
            }
            res.status(404);
            return toJson(new ErrorClass("User with id " + id + " not found"));
        });


    }
}
