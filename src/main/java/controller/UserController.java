/**
 * Created by yoges on 7/8/2016.
 */

package controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import model.*;
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
        get("/users", (req, res) -> this.userEngine.getAllUsers(), json());
    }
}
