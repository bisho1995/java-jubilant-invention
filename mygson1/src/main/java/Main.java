import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args) {
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("ob1p1", "ob1p1v1");

        JsonObject obj2 = new JsonObject();
        obj2.addProperty("ob2p1", "ob2p1v1");

        obj1.add("ob1p2", obj2);

        String obj1Json = obj1.toString();

        System.out.println(obj1Json);
    }
}
