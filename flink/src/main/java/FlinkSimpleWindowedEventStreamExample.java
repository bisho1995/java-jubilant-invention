import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FlinkSimpleWindowedEventStreamExample {
    private static final Logger logger = LoggerFactory.getLogger(FlinkSimpleWindowedEventStreamExample.class);

    private static final AscendingTimestampExtractor<Integer> ascendingTimestampExtractor = new AscendingTimestampExtractor<>() {
        @Override
        public long extractAscendingTimestamp(Integer integer) {
            return System.currentTimeMillis();
        }
    };

    private static final AllWindowFunction<Integer, List<Integer>, TimeWindow> allWindowFunction = new AllWindowFunction<>() {
        @Override
        public void apply(TimeWindow timeWindow, Iterable<Integer> iterable, Collector<List<Integer>> collector) throws Exception {
            List<Integer> list = new ArrayList<>();
            for (Integer p : iterable) {
                list.add(p);
            }

            collector.collect(list);
        }
    };

    public static void init() throws Exception {

        StreamExecutionEnvironment env = getEnvironment();

        DataStreamSource<Integer> stream = env.fromElements(2, 3, 5, 10, 11, 12, 4, 1, 2, 55, 1);
        processStream(stream).print();
        env.execute();

    }

    static SingleOutputStreamOperator<List<Integer>> processStream(DataStreamSource<Integer> stream) {
        return stream
                .assignTimestampsAndWatermarks(ascendingTimestampExtractor)
                .name("assigned-timestamps-and-watermarks")
                .windowAll(TumblingEventTimeWindows.of(Time.seconds(2)))
                .apply(allWindowFunction);


    }

    static StreamExecutionEnvironment getEnvironment() {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        return environment;
    }
}
