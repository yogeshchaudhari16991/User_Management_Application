package util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by yoges on 7/8/2016.
 */

public class JsonUtil {

    public static String toJson(Object object) {

        return new Gson().toJson(object);

    }

    public static ResponseTransformer json() {

        return JsonUtil::toJson;

    }

}
