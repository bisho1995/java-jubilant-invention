package org.example2;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class SparkLearn3 {
    public static void main(String[] args) {
        List<Schema> arrayList = new ArrayList<>();
        arrayList.add(new Schema("name1", 1, true));
        arrayList.add(new Schema("name2", 2, true));
        arrayList.add(new Schema("name3", 3, false));

//        Logger.getLogger("org.apache").setLevel(Level.WARN);

        SparkConf sparkConf = new SparkConf().setAppName("startingSpark").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);


        JavaRDD<Schema> myRdd = sc.parallelize(arrayList);
        int numPartitions = myRdd.getNumPartitions();
        log.info("number of partitions received for data => {}", numPartitions);
        long count = myRdd.count();
        log.info("count of all the data => {}",  count);

        myRdd.foreach(x -> {
            int partitionId = TaskContext.getPartitionId();
            log.info("value => {}, partitionId => {}", x, partitionId);
        });

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        sc.close();
    }

    @AllArgsConstructor
    @ToString
    private static class Schema implements Serializable {
        String name;
        Integer version;
        Boolean isValid;
    }
}
