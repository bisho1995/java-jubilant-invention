package org.example.serialization;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.ToString;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This demo proves that extra properties in the string don't break
 * the deserialization.
 */
public class Deserialization {
    @ToString
    private static final class Class1 {
        String name;
    }
    @SneakyThrows
    public static void main(String[] args) {
        String path = Deserialization.class.getClassLoader().getResource("json1.json").getPath();

        String content = new String(Files.readAllBytes(Paths.get(path)));

        System.out.println(content);

        Class1 class1 = new Gson().fromJson(content, Class1.class);

        System.out.println(class1.toString());
    }
}
