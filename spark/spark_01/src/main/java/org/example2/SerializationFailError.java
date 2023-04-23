package org.example2;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class SerializationFailError {
    @SneakyThrows
    public static void main(String[] args) {
        NonSerializablePojo obj1 = new NonSerializablePojo();

        List<NonSerializablePojo> arrayList = new ArrayList<>();
        arrayList.add(obj1);

        SparkConf sparkConf = new SparkConf()
                .setAppName("startingSpark")
                .setMaster("local[*]");

        JavaSparkContext sc = new JavaSparkContext(sparkConf);


        JavaRDD<NonSerializablePojo> myRdd = sc.parallelize(arrayList);

        myRdd.foreach(x -> System.out.println("x => " + x));

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        sc.close();
    }

    @ToString
    @AllArgsConstructor
    private static final class NonSerializablePojo implements Serializable {
        public static final String name = "Java";

        private void writeObject(java.io.ObjectOutputStream out)
                throws IOException {
            throw new NotSerializableException();
        }

        private void readObject(java.io.ObjectInputStream in)
                throws IOException, ClassNotFoundException {
            throw new NotSerializableException();
        }

        private void readObjectNoData() throws ObjectStreamException {
            throw new NotSerializableException();
        }

    }
}
