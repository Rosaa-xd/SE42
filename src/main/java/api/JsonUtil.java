package api;

import com.google.gson.Gson;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import spark.ResponseTransformer;

/**
 * Created by Shadowphoenix on 07/01/2018
 */
public class JsonUtil {
    public static JSONParser jsonParser = new JSONParser();
    public static JSONObject jsonObject;

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }

    public static void parse (String obj) {
        try {
            jsonObject = (JSONObject) jsonParser.parse(obj);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
