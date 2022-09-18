import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

import java.io.*;

public class SerializationDeserialization {
    @Builder
    @Data
    private static class Ser1 implements Serializable {
        String name;
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Ser1 obj1 = Ser1.builder().name("test1").build();

        final String fileName = "ser1.ser";
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(obj1);

        FileInputStream file = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(file);
        Object object1 = in.readObject();
        Ser1 obj2 = (Ser1) object1;

        Gson gson = new Gson();
        String jsonMessage = gson.toJson(object1);
        Object obj3 = gson.fromJson(jsonMessage, Object.class);
        Ser1 obj4 = (Ser1) obj3;

        System.out.println("obj2"+obj2.toString());
        System.out.println("obj3"+obj3.toString());
        System.out.println("obj4"+ obj4);
    }
}
