package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.util.JSON;
import model.Address;
import model.Company;
import model.User;
import spark.ResponseTransformer;

import javax.servlet.ServletInputStream;
import java.io.InputStreamReader;

/**
 * Created by yoges on 7/8/2016.
 */

public class JsonUtil {

    private static Gson g;

    static {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Address.class, new AddressInstanceCreator());
        gb.registerTypeAdapter(Company.class, new ComapnyInstanceCreator());
        g = gb.create();
    }

    public static String toJson(Object object) {
        return g.toJson(object);
    }

    public static Object fromJson(String json) {
        return g.fromJson(json, User.class);
    }

    public static ResponseTransformer json() {

        return JsonUtil::toJson;

    }

}
