package util;

// imports
import com.google.gson.Gson;
import model.User;
import spark.ResponseTransformer;

/**
 * Created by yoges on 7/8/2016.
 */

public class JsonUtil {

    // Static Utility methods
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
