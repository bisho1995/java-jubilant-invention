import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FlinkSimpleWindowedEventStreamExample {
    public static void init() throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);


        environment
                .fromElements(2,3,5)
                .assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<>() {
                    private long timestamp = 0;

                    @Nullable
                    @Override
                    public Watermark getCurrentWatermark() {
                        return new Watermark(timestamp);
                    }

                    @Override
                    public long extractTimestamp(Integer payload, long l) {
                        timestamp = System.currentTimeMillis();

                        return timestamp;
                    }
                })
                .name("assigned-timestamps-and-watermarks")
                .windowAll(TumblingEventTimeWindows.of(Time.seconds(2)))
                .apply(new AllWindowFunction<Integer, List<Integer>, TimeWindow>() {
                    @Override
                    public void apply(TimeWindow timeWindow, Iterable<Integer> iterable, Collector<List<Integer>> collector) throws Exception {
                        List<Integer> list = new ArrayList<>();
                        for(Integer p: iterable) {
                            list.add(p);
                        }

                        collector.collect(list);
                    }
                })
                .print();

        environment.execute();
    }
}
