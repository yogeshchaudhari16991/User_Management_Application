/**
 * Created by yoges on 7/8/2016.
 */

package controller;

//imports
import com.google.gson.*;
import model.*;
import spark.Spark;
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
