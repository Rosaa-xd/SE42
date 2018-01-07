package api;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by Shadowphoenix on 07/01/2018
 */
public class JsonUtil {
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}
