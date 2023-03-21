package org.example;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Double> arrayList = new ArrayList<>();
        arrayList.add(1.0);
        arrayList.add(2.0);
        arrayList.add(3.0);
        arrayList.add(4.0);

//        Logger.getLogger("org.apache").setLevel(Level.WARN);
        
        SparkConf sparkConf = new SparkConf().setAppName("startingSpark").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);


        JavaRDD<Double> myRdd = sc.parallelize(arrayList);

        myRdd.foreach(x->{
            System.out.println("x => "+x);
        });

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        sc.close();
    }
}