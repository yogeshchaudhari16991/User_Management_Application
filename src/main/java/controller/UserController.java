/**
 * Created by yoges on 7/8/2016.
 */

package controller;

//imports
import model.*;
import util.ErrorClass;
//static imports
import static spark.Spark.*;
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
            for (User user : this.userEngine.getAllUsers()) {
                usersInJSON.append(toJson(user));
            }
            if (usersInJSON.length() > 0) {
                res.type("application/json");
                res.status(302);
                return usersInJSON;
            } else {
                res.status(404);
                return (new ErrorClass("There are no user entries in database")).getMessage();
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
                return (new ErrorClass("Conflict Occurred: User with id already present in database")).getMessage();
            }
        });

        get("/user/:id", (req, res) -> {
            String id = req.params(":id");

            User user = userEngine.getUser(id);
            if (user != null) {
                res.type("application/json");
                res.status(302);  // HTTP code for Found
                return toJson(user);
            }
            res.status(404);  // HTTP code for Not Found
            return (new ErrorClass("User with id " + id + " not found")).getMessage();
        });

        put("/user/:id", (req, res) -> {
            System.out.println("In put");
            String id = req.params(":id");
            String body = req.body();
            if (this.userEngine.updateUser(id, body)) {
                res.status(302);
                res.redirect("/users");
                return "";
            }
            res.status(404);
            return (new ErrorClass("User with id " + id + " not found")).getMessage();
        });

        get("/:anything", (req, res) -> {
            res.status(501);
           return (new ErrorClass(" Request not implemented... Stay tuned!!")).getMessage();
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
