package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Address;
import model.Company;
import model.User;
import spark.ResponseTransformer;

/**
 * Created by yoges on 7/8/2016.
 */

public class JsonUtil {

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static Object fromJson(String json) {
        return new Gson().fromJson(json, User.class);
    }

    public static ResponseTransformer json() {

        return JsonUtil::toJson;

    }

}
