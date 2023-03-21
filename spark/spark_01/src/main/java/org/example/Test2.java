package org.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Scanner;

@Slf4j
public class Test2 {
    private static final String SPARK_APP_NAME = "spark-app-name-1";

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName(SPARK_APP_NAME).setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);

        SparkEnv sparkEnv = SparkEnv.get();
        log.info("executorId => {}, conf => {}, driverTmpDir => {}, logName => {}",
                sparkEnv.executorId().toString(),
                sparkEnv.conf().getAll(),
                sparkEnv.driverTmpDir().get(),
                sparkEnv.logName()
        );

        (new Scanner(System.in)).nextLine();
        sc.close();
    }
}
