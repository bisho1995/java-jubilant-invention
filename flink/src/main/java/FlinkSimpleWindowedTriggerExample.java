import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FlinkSimpleWindowedTriggerExample {
    private static Logger logger = LoggerFactory.getLogger(FlinkSimpleWindowedEventStreamExample.class);

    public static void init() throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);


        environment
                .fromElements(2,3,5,10,11,12,4,1,2,55,1)
                .assignTimestampsAndWatermarks(new AscendingTimestampExtractor<Integer>() {
                    @Override
                    public long extractAscendingTimestamp(Integer integer) {
                        return System.currentTimeMillis();
                    }
                })
                .name("assigned-timestamps-and-watermarks")
                .windowAll(TumblingEventTimeWindows.of(Time.seconds(2)))
                .trigger(new Trigger<Integer, TimeWindow>() {
                    @Override
                    public TriggerResult onElement(Integer element, long l, TimeWindow timeWindow, TriggerContext ctx) throws Exception {
                        ValueState<Integer> totalMessages = ctx.getPartitionedState(new ValueStateDescriptor<>("totalMessages", Types.INT));

                        if(totalMessages.value() == null) {
                            totalMessages.update(1);
                        } else {
                            totalMessages.update(totalMessages.value()+1);
                        }

                        logger.info("onElement called with value = {} totalMessages = {}", element, totalMessages.value());

                        if(totalMessages.value() >=3) {
                            totalMessages.update(0);
                            return TriggerResult.FIRE_AND_PURGE;
                        }
                        return TriggerResult.CONTINUE;
                    }

                    @Override
                    public TriggerResult onProcessingTime(long l, TimeWindow timeWindow, TriggerContext triggerContext) throws Exception {
                        logger.info("onProcessingTime l = {}",l);
                        return TriggerResult.CONTINUE;
                    }

                    @Override
                    public TriggerResult onEventTime(long l, TimeWindow timeWindow, TriggerContext triggerContext) throws Exception {
                        logger.info("onEventTime l = {}",l);
                        return TriggerResult.FIRE_AND_PURGE;
                    }

                    @Override
                    public void clear(TimeWindow timeWindow, TriggerContext ctx) throws Exception {
                        ctx.getPartitionedState(new ValueStateDescriptor<>("totalMessages", Types.INT)).clear();
                    }
                })
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
