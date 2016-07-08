/**
 * Created by yogesh on 7/7/2016.
 */
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}
