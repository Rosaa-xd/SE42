import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

/**
 * Created by Shadowphoenix on 14-11-2017.
 */
public class Main {
    public static void main(String[] args) {
        get("/hello", ((request, response) -> "Hello World"));
    }
}
