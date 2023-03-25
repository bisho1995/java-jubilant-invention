package org.example2;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskEndReason;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerTaskEnd;
import org.apache.spark.scheduler.TaskInfo;

import java.util.Arrays;
import java.util.Scanner;

@Slf4j
public class SparkOOMTest1 {
    public static void main(String[] args) {
        Logger.getLogger("org.apache").setLevel(Level.WARN);

        SparkConf sparkConf = new SparkConf().setAppName("startingSpark").setMaster("local[*]");
        sparkConf.set("spark.executor.memory", "500m"); // Set executor memory to be very low

        JavaSparkContext sc = new JavaSparkContext(sparkConf);

        sc.sc().addSparkListener(new SparkEventListener());

        JavaRDD<String> rdd = sc.parallelize(Arrays.asList("1", "2", "3", "4", "5"));

        JavaRDD<Integer> result = rdd.map(s -> {
            byte[] bytes = new byte[100 * 1000 * 1024 * 1024]; // Allocate 150MB memory, which exceeds the executor memory limit
            log.info("s => {}", s);
            return Integer.parseInt(s);
        });

        long count = result.count();
        log.info("count => {}", count);


        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        sc.close();
    }

    @Slf4j
    private static final class SparkEventListener extends SparkListener {
        @Override
        public void onTaskEnd(SparkListenerTaskEnd taskEnd) {
            final TaskInfo taskInfo = taskEnd.taskInfo();
            TaskEndReason reason = taskEnd.reason();
            boolean failed = taskEnd.taskInfo().failed();
            int stageId = taskEnd.stageId();
            long taskId = taskEnd.taskInfo().taskId();
            log.info("onTaskEnd taskId: {} failed: {} with reason: {}", taskId, failed, reason);
        }
    }
}
