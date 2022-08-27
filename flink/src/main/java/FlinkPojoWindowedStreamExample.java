import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class FlinkPojoWindowedStreamExample {

    @Builder
    @Getter
    @Setter
    @ToString
    public static class Payload {
        private transient final String driverId;

        @SerializedName("Barrier_Free")
        private final String barrier_free = "No";

        @SerializedName("id")
        private final String id;
    }
    public static void init() throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        Payload p1 = Payload.builder().id(UUID.randomUUID().toString()).driverId(UUID.randomUUID().toString()).build();
        Payload p2 = Payload.builder().id(UUID.randomUUID().toString()).driverId(UUID.randomUUID().toString()).build();

        InputStream propertiesStream = MainFlink.class.getClassLoader().getResourceAsStream("EventWindows/flink.properties");
        Properties properties = new Properties();
        properties.load(propertiesStream);

        environment
                .fromElements(p1,p2)
                .assignTimestampsAndWatermarks(new AscendingTimestampExtractor<Payload>() {
                    @Override
                    public long extractAscendingTimestamp(Payload payload) {
                        return System.currentTimeMillis();
                    }
                })
                .name("assigned-timestamps-and-watermarks")
                .windowAll(TumblingEventTimeWindows.of(Time.seconds(Long.parseLong(properties.getProperty("flink.window.interval")))))
                .apply(new AllWindowFunction<Payload, List<Payload>, TimeWindow>() {
                    @Override
                    public void apply(TimeWindow timeWindow, Iterable<Payload> iterable, Collector<List<Payload>> collector) throws Exception {
                        List<Payload> list = new ArrayList<>();
                        for(Payload p: iterable) {
                            list.add(p);
                        }

                        collector.collect(list);
                    }
                })
                .print();

        environment.execute();
    }
}
