package org.example2;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import java.util.Scanner;

@Slf4j
public class SparkGenerateDataSet {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("startingSpark").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);

        SparkSession sparkSession = SparkSession.builder().appName("startingSpark").config(sparkConf).getOrCreate();

        Dataset<Long> dataset = sparkSession.range(1, 10).toDF().as(Encoders.LONG());

        dataset.show();
        log.info("dataset => {}", dataset.collect());

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        sc.close();
    }
}
